package com.believe.documentation.web;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping(value = "/tests", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/tests", tags = "tests", description = "Operations about user")
public class TestController {

  @RequestMapping(value = "/{username}", method = PUT)
  @ResponseBody
  @ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Invalid user supplied",
      responseHeaders = {
        @ResponseHeader(
          name = "X-Rate-Limit-Limit",
          description = "The number of allowed requests in the current period"),
        @ResponseHeader(
          name = "X-Rate-Limit-Remaining",
          description = "The number of remaining requests in the current period")
      }),
    @ApiResponse(code = 404, message = "User not found",
      responseHeaders = {
        @ResponseHeader(
          name = "X-Rate-Limit-Limit",
          description = "The number of allowed requests in the current period"),
        @ResponseHeader(
          name = "X-Rate-Limit-Remaining",
          description = "The number of remaining requests in the current period")
      })})
  public ResponseEntity<String> updateUser(
    @ApiParam(value = "name that need to be deleted", required = true)
    @PathVariable("username") String username) {
    return ResponseEntity.ok(username);
  }

}
