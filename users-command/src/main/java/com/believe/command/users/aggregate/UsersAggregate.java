package com.believe.command.users.aggregate;

import com.believe.api.users.event.UsersCreatedEvent;
import com.believe.command.users.command.CreateUsersCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
public class UsersAggregate {

  @AggregateIdentifier
  private String id;
  private String username;

  public UsersAggregate() {
  }

  @CommandHandler
  public UsersAggregate(CreateUsersCommand command) {
    apply(new UsersCreatedEvent(command.getId(), command.getUsername()));
  }

  @EventSourcingHandler
  public void on(UsersCreatedEvent event) {
    this.id = event.getId();
    this.username = event.getUsername();
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
