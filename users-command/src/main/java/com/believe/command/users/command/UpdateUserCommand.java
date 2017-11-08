package com.believe.command.users.command;

import com.believe.api.users.model.UserId;
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
public class UpdateUserCommand {

  @NotNull
  @TargetAggregateIdentifier
  private final UserId id;
  @NotBlank
  private final String username;

  public UpdateUserCommand(String id, String username) {
    this.username = username;
    this.id = new UserId(id);
  }

}
