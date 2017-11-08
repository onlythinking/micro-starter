package com.believe.command.users.aggregate;

import com.believe.api.users.model.UserId;
import com.believe.command.users.command.AuthenticateUserCommand;
import com.believe.command.users.command.CreateUserCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.stereotype.Component;

/**
 * <p> Users aggregate Handler </p>
 *
 * @author Li Xingping
 */
@Component
public class UserCommandHandler {

  private Repository<UserAggregate> repository;
  private EventBus eventBus;

  public UserCommandHandler(Repository<UserAggregate> repository, EventBus eventBus) {
    this.repository = repository;
    this.eventBus = eventBus;
  }

  @CommandHandler
  public UserId handleCreateUser(CreateUserCommand command) throws Exception {
    UserId identifier = command.getIdentifier();
    repository.newInstance(() -> new UserAggregate(identifier, command.getUsername(), command.getPassword()));
    return identifier;
  }

  @CommandHandler
  public void handleAuthenticateUser(AuthenticateUserCommand command) {
    // todo
  }

}
