package com.believe.command.users.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
public class CreateUsersCommand {

  @TargetAggregateIdentifier
  private String id;
  private String username;

  public CreateUsersCommand(String username) {
    this.id = UUID.randomUUID().toString();
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
