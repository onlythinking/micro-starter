package com.believe.command.users.web;

import com.believe.api.users.model.UsersId;
import com.believe.command.users.command.*;
import com.believe.command.users.web.dto.UserDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RestController
public class UsersCommandController {

  @Autowired
  private CommandGateway commandGateway;

  @PostMapping("/create")
  public void create(@Valid @RequestBody UserDto userDto) {
    CreateUsersCommand command = new CreateUsersCommand(userDto.getUsername(), userDto.getPassword());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/active")
  public void activeUsers(@Valid @RequestBody UserDto userDto) {
    ActiveUsersCommand command = new ActiveUsersCommand(new UsersId(userDto.getId()), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/dis_active")
  public void disActiveUsers(@Valid @RequestBody UserDto userDto) {
    DisActiveUsersCommand command = new DisActiveUsersCommand(new UsersId(userDto.getId()), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/bind_sa")
  public void bindSocialAccount(@Valid @RequestBody UserDto userDto) {
    BindSocialAccountCommand command = new BindSocialAccountCommand(new UsersId(userDto.getId()), userDto.getUsername(), userDto.getSocialAccountType(), userDto.getData());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/unbind_sa")
  public void unBindSocialAccount(@Valid @RequestBody UserDto userDto) {
    UnBindSocialAccountCommand command = new UnBindSocialAccountCommand(new UsersId(userDto.getId()), userDto.getUsername(), userDto.getSocialAccountType(), userDto.getData());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/update")
  public void update(@Valid @RequestBody UserDto userDto) {
    UpdateUsersCommand command = new UpdateUsersCommand(userDto.getId(), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

}

