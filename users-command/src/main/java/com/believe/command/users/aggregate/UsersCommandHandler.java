package com.believe.command.users.aggregate;

import com.believe.api.users.model.UsersId;
import com.believe.command.users.command.AuthenticateUsersCommand;
import com.believe.command.users.command.CreateUsersCommand;
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
public class UsersCommandHandler {

  private Repository<UsersAggregate> repository;
  private EventBus eventBus;

  public UsersCommandHandler(Repository<UsersAggregate> repository, EventBus eventBus) {
    this.repository = repository;
    this.eventBus = eventBus;
  }

  @CommandHandler
  public UsersId handleCreateUser(CreateUsersCommand command) throws Exception {
    UsersId identifier = command.getIdentifier();
    repository.newInstance(() -> new UsersAggregate(identifier, true, command.getUsername(), command.getPasswordHash()));
    return identifier;
  }

  @CommandHandler
  public void handleAuthenticateUser(AuthenticateUsersCommand command) {
    // todo
  }

}
