package com.believe.commons.api;

import lombok.Getter;

/**
 * <p> The Domain object throws </p>
 *
 * @author Li Xingping
 */
@Getter
public class DomainException extends RuntimeException {

  private final int errorCode;

  private DomainException(int errorCode) {
    this.errorCode = errorCode;
  }

  private DomainException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public DomainException(int errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public static DomainException of(int errorCode) {
    return new DomainException(errorCode);
  }

  public static DomainException of(int errorCode, String message) {
    return new DomainException(errorCode, message);
  }

  public static DomainException of(String message) {
    return new DomainException(ErrorCode.UN_KNOW, message);
  }

  public static DomainException of(String message, Throwable cause) {
    return new DomainException(ErrorCode.UN_KNOW, message);
  }

}
