package com.believe.command.users.aggregate;

import com.believe.api.users.event.UsersCreatedEvent;
import com.believe.command.users.command.CreateUsersCommand;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
@Aggregate
@Data
public class UsersAggregate {

  @AggregateIdentifier
  private String id;
  private String username;

  @CommandHandler
  public UsersAggregate(CreateUsersCommand command) {
    apply(new UsersCreatedEvent(command.getId(), command.getUsername()));
  }

  @EventSourcingHandler
  public void on(UsersCreatedEvent event) {
    this.id = event.getId();
    this.username = event.getUsername();
  }

}
