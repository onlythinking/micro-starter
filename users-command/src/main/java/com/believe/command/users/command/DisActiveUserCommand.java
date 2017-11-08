package com.believe.command.users.command;

import com.believe.api.users.model.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisActiveUserCommand {
  @NotNull
  @TargetAggregateIdentifier
  private UserId identifier;
  @NotBlank
  private String username;
}
