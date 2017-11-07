package com.believe.command.users.aggregate;

import com.believe.api.users.event.*;
import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.SocialId;
import com.believe.api.users.model.UsersId;
import com.believe.command.users.command.ActiveUsersCommand;
import com.believe.command.users.command.BindSocialAccountCommand;
import com.believe.command.users.command.DisActiveUsersCommand;
import com.believe.command.users.command.UnBindSocialAccountCommand;
import com.believe.commons.api.DomainException;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

import static com.believe.commons.api.ErrorCode.*;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
@Aggregate
@Data
@NoArgsConstructor
public class UsersAggregate {

  @NotNull
  @AggregateIdentifier
  private UsersId identifier;
  private boolean activated;
  private String username;
  private String passwordHash;

  @AggregateMember
  private Map<SocialAccountType, SocialAccountEntity> socialAccounts = Maps.newHashMapWithExpectedSize(SocialAccountType.values().length);

  public UsersAggregate(UsersId identifier, String username, String password) {
    apply(new UsersCreatedEvent(identifier, username, hashOf(password)));
  }

  public boolean authenticate(String password) {
    boolean success = this.passwordHash.equals(hashOf(password));
    if (success) {
      apply(new UserAuthenticatedEvent(this.identifier));
    }
    return success;
  }

  @CommandHandler
  public void activeUsers(ActiveUsersCommand command) {
    if (this.activated) {
      throw DomainException.of(USER_ACTIVE_STATE_INVALID, "Already activated.");
    }
    apply(new UsersActivatedEvent(command.getIdentifier(), command.getUsername(), true));
  }

  @CommandHandler
  public void disActiveUsers(DisActiveUsersCommand command) {
    if (!this.activated) {
      throw DomainException.of(USER_ACTIVE_STATE_INVALID);
    }
    apply(new UsersDisActivatedEvent(command.getIdentifier(), command.getUsername(), false));
  }

  @CommandHandler
  public void bindSocialAccount(BindSocialAccountCommand command) {
    checkUsersState();
    apply(new SocialAccountBindEvent(command.getIdentifier(), findSocialAccount(command.getSocialAccountType(), command.getData()).getSocialId(), command.getSocialAccountType(), command.getData()));
  }

  @CommandHandler
  public void unBindSocialAccount(UnBindSocialAccountCommand command) {
    checkUsersState();
    apply(new SocialAccountUnbindEvent(command.getIdentifier(), findSocialAccount(command.getSocialAccountType(), command.getData()).getSocialId(), command.getSocialAccountType(), command.getData()));
  }

  private SocialAccountEntity findSocialAccount(SocialAccountType socialAccountType, String accountNo) {
    return Optional.ofNullable(this.socialAccounts.get(socialAccountType))
      .orElse(new SocialAccountEntity(new SocialId(), true, null, accountNo));
  }

  private boolean checkUsersState() {
    if (!this.activated) {
      throw DomainException.of(USER_ACTIVE_STATE_INVALID);
    }
    return true;
  }

  @EventHandler
  public void on(UsersCreatedEvent event) {
    this.identifier = event.getIdentifier();
    this.username = event.getUsername();
    this.passwordHash = event.getPassword();
    this.activated = true;
  }

  @EventSourcingHandler
  public void on(UsersActivatedEvent event) {
    this.identifier = event.getIdentifier();
    this.activated = event.isActivated();
  }

  @EventSourcingHandler
  public void on(UsersDisActivatedEvent event) {
    this.identifier = event.getIdentifier();
    this.activated = event.isActivated();
  }

  @EventSourcingHandler
  public void on(SocialAccountBindEvent event) {
    this.identifier = event.getIdentifier();
    this.socialAccounts.put(event.getSocialAccountType(), new SocialAccountEntity(new SocialId(), true, null, event.getAccountNo()));
  }

  @EventSourcingHandler
  public void on(SocialAccountUnbindEvent event) {
    this.identifier = event.getIdentifier();
    this.socialAccounts.remove(event.getSocialAccountType());
  }

  private String hashOf(String password) {
    try {
      return DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      //Ignored.
    }
    return null;
  }

}
