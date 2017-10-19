package com.believe.command.users.web;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
//@RestController
//@RequestMapping(value = "/users")
public class UsersController {

  @Autowired
  private CommandGateway commandGateway;

}
