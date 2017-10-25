package com.believe.command.users.aggregate;

import com.believe.api.users.event.UsersCreatedEvent;
import com.believe.api.users.event.UsersUpdatedEvent;
import com.believe.api.users.model.UsersId;
import com.believe.command.users.command.CreateUsersCommand;
import com.believe.command.users.command.UpdateUsersCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;

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
  private UsersId id;
  private String username;

  @CommandHandler
  public UsersAggregate(CreateUsersCommand command) {
    apply(new UsersCreatedEvent(command.getId(), command.getUsername()));
  }

  @CommandHandler
  public void update(UpdateUsersCommand command) {
    apply(new UsersUpdatedEvent(command.getId(), command.getUsername()));
  }

  @EventSourcingHandler
  public void on(UsersCreatedEvent event) {
    this.id = event.getId();
    this.username = event.getUsername();
  }

  @EventSourcingHandler
  public void on(UsersUpdatedEvent event) {
    this.username = event.getUsername();
  }

}
