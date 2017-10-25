package com.believe.command.users.web;

import com.believe.command.users.command.CreateUsersCommand;
import com.believe.command.users.command.UpdateUsersCommand;
import com.believe.command.users.web.dto.UserDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RestController
@RequestMapping("/users")
public class UsersCommandController {

  @Autowired
  private CommandGateway commandGateway;

  @PostMapping("/create")
  public void create(@RequestBody UserDto userDto) {
    CreateUsersCommand command = new CreateUsersCommand(userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

  @PostMapping("/update")
  public void update(@RequestBody UserDto userDto) {
    UpdateUsersCommand command = new UpdateUsersCommand(userDto.getId(), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

}

