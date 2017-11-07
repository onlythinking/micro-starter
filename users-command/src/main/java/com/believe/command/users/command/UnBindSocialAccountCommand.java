package com.believe.command.users.command;

import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.UsersId;
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
public class UnBindSocialAccountCommand {

  @NotNull
  @TargetAggregateIdentifier
  private UsersId identifier;
  @NotBlank
  private String username;
  @NotNull
  private SocialAccountType socialAccountType;
  @NotBlank
  private String data;
}
