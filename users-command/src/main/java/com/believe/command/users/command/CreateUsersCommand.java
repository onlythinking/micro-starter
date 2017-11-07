package com.believe.command.users.command;

import com.believe.api.users.model.UsersId;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Data
public class CreateUsersCommand {

  @NotNull
  @TargetAggregateIdentifier
  private final UsersId identifier;
  @NotBlank
  private final String username;
  private String password;

  public CreateUsersCommand(String username, String password) {
    this.username = username;
    this.password = password;
    this.identifier = new UsersId();
  }

}
