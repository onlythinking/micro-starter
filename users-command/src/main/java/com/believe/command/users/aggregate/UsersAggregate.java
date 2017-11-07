package com.believe.command.users.aggregate;

import com.believe.api.users.event.*;
import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.UsersId;
import com.believe.command.users.command.*;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;

import java.util.Map;

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
  private Map<SocialAccountType, SocialAccountEntity> authenticationEntities = Maps.newHashMapWithExpectedSize(SocialAccountType.values().length);

  public UsersAggregate(UsersId identifier, boolean activated, String username, String passwordHash) {
    this.identifier = identifier;
    this.activated = activated;
    this.username = username;
    this.passwordHash = passwordHash;
  }

  @CommandHandler
  public void activatedUsers(ActiveUsersCommand command) {
    if (this.activated) {
      throw new IllegalStateException("Illegal state.");
    }
    apply(new UsersActivatedEvent(command.getIdentifier(), command.getUsername(), true));
  }

  @CommandHandler
  public void disActivatedUsers(DisActiveUsersCommand command) {
    if (this.activated) {
      throw new IllegalStateException("Illegal state.");
    }
    apply(new UsersDisActivatedEvent(command.getIdentifier(), command.getUsername(), false));
  }

  @CommandHandler
  public void bindSocialAccount(BindSocialAccountCommand command) {
    checkUsersState();
    apply(new SocialAccountBindEvent(command.getIdentifier(), command.getUsername(), command.getSocialAccountType(), command.getData()));
  }

  @CommandHandler
  public void unBindSocialAccount(UnBindSocialAccountCommand command) {
    checkUsersState();
    if (this.authenticationEntities.isEmpty() || this.authenticationEntities.size() == 1) {
      //todo exception commons handle
      throw new IllegalStateException("Illegal state.");
    }
    apply(new SocialAccountUnbindEvent(command.getIdentifier(), command.getUsername(), command.getSocialAccountType(), command.getData()));
  }

  private boolean checkUsersState() {
    if (!this.activated) {
      throw new IllegalStateException("Illegal state.");
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
    this.username = event.getUsername();
    this.activated = event.isActivated();
  }

  @EventSourcingHandler
  public void on(UsersDisActivatedEvent event) {
    this.identifier = event.getIdentifier();
    this.username = event.getUsername();
    this.activated = event.isActivated();
  }

  @EventSourcingHandler
  public void on(SocialAccountUnbindEvent event) {
    this.identifier = event.getIdentifier();
    this.username = event.getUsername();
    this.authenticationEntities.remove(event.getSocialAccountType());
  }

}
