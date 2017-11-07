package com.believe.command.users.command;

import com.believe.api.users.model.SocialAccountType;
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
public class ActiveUsersCommand {
  @NotNull
  @TargetAggregateIdentifier
  private final UsersId identifier;
  @NotBlank
  private String username;
}
