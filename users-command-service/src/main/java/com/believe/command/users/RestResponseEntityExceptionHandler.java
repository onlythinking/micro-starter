package com.believe.command.users;

import com.believe.commons.api.DomainException;
import com.believe.commons.api.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <p> RestResponseEntityExceptionHandler </p>
 *
 * @author Li Xingping
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  public RestResponseEntityExceptionHandler() {
    super();
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status,
                                                                final WebRequest request) {
    final String bodyOfResponse = "HttpMessageNotReadableException";
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
                                                                final WebRequest request) {
    final String bodyOfResponse = "MethodArgumentNotValidException";
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({CommandExecutionException.class})
  protected ResponseEntity<Object> handleCommandExecution(final RuntimeException cex, final WebRequest request) {
    final String bodyOfResponse = "CommandExecutionException";
    if (null != cex.getCause()) {
      log.error("CAUSED BY: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
      if (cex.getCause() instanceof ConcurrencyException) {
        return handleExceptionInternal(cex, bodyOfResponse + " - Concurrency issue", new HttpHeaders(), HttpStatus.CONFLICT, request);
      }
    }
    return handleExceptionInternal(cex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
  protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
    final String bodyOfResponse = "DataAccessException";
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
    final String bodyOfResponse = "DataIntegrityViolationException";
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
  public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
    final String bodyOfResponse = "Internal Error";
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler({JSR303ViolationException.class})
  public ResponseEntity<Object> handleValidation(final JSR303ViolationException ex, final WebRequest request) {
    final String bodyOfResponse = ex.getViolations().toString();
    log.error("Validation error", ex);
    return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({DomainException.class})
  public ResponseEntity<Object> handleDomainException(final DomainException ex, final WebRequest request) {
    log.error("Domain error", ex);
    return new ResponseEntity<Object>(new ErrorMessage(ex.getErrorCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

}