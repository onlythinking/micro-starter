package com.believe.command.users.aggregate;

import com.believe.api.users.domain.User;
import com.believe.api.users.model.UserId;
import com.believe.command.users.client.UsersQueryServiceClient;
import com.believe.command.users.command.AuthenticateUserCommand;
import com.believe.command.users.command.CreateUserCommand;
import com.believe.commons.api.DomainException;
import com.believe.commons.api.ErrorCode;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.hateoas.Resource;
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
  private UsersQueryServiceClient usersQueryClient;

  public UserCommandHandler(Repository<UserAggregate> repository, EventBus eventBus, UsersQueryServiceClient usersQueryClient) {
    this.repository = repository;
    this.eventBus = eventBus;
    this.usersQueryClient = usersQueryClient;
  }

  @CommandHandler
  public UserId handleCreateUser(CreateUserCommand command) throws Exception {
    UserId identifier = command.getIdentifier();

    Resource<User> userResource = usersQueryClient.findByUsername(command.getUsername());
    if (userResource != null && userResource.getContent() != null) {
      throw DomainException.of(ErrorCode.USER_ALREADY_EXIST);
    }
    repository.newInstance(() -> new UserAggregate(identifier, command.getUsername(), command.getPassword()));
    return identifier;
  }

  @CommandHandler
  public void handleAuthenticateUser(AuthenticateUserCommand command) {
    // todo
  }

}
