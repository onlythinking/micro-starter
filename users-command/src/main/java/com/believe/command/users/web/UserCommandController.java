package com.believe.command.users.web;

import com.believe.api.users.domain.User;
import com.believe.api.users.model.UserId;
import com.believe.command.users.client.UsersQueryServiceClient;
import com.believe.command.users.command.*;
import com.believe.command.users.web.dto.UserDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RestController
public class UserCommandController {

  @Autowired
  private CommandGateway commandGateway;

  @Autowired
  private UsersQueryServiceClient usersQueryClient;

  @GetMapping("/t/{username}")
  public Resource<User> tt(@PathVariable("username")String username) {
    return usersQueryClient.findByUsername(username);
  }

  @PostMapping("/create")
  public void create(@Valid @RequestBody UserDto userDto) {
    CreateUserCommand command = new CreateUserCommand(userDto.getUsername(), userDto.getPassword());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/active")
  public void activeUsers(@Valid @RequestBody UserDto userDto) {
    ActiveUserCommand command = new ActiveUserCommand(new UserId(userDto.getId()), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/dis_active")
  public void disActiveUsers(@Valid @RequestBody UserDto userDto) {
    DisActiveUserCommand command = new DisActiveUserCommand(new UserId(userDto.getId()), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/bind_sa")
  public void bindSocialAccount(@Valid @RequestBody UserDto userDto) {
    BindSocialAccountCommand command = new BindSocialAccountCommand(new UserId(userDto.getId()), userDto.getUsername(), userDto.getSocialAccountType(), userDto.getData());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/unbind_sa")
  public void unBindSocialAccount(@Valid @RequestBody UserDto userDto) {
    UnBindSocialAccountCommand command = new UnBindSocialAccountCommand(new UserId(userDto.getId()), userDto.getUsername(), userDto.getSocialAccountType(), userDto.getData());
    commandGateway.sendAndWait(command);
  }

  @PutMapping("/update")
  public void update(@Valid @RequestBody UserDto userDto) {
    UpdateUserCommand command = new UpdateUserCommand(userDto.getId(), userDto.getUsername());
    commandGateway.sendAndWait(command);
  }

}

