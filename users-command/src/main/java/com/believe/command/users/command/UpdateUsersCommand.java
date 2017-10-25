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
public class UpdateUsersCommand {

  @NotNull
  @TargetAggregateIdentifier
  private final UsersId id;
  @NotBlank
  private final String username;

  public UpdateUsersCommand(String id, String username) {
    this.username = username;
    this.id = new UsersId(id);
  }

}
