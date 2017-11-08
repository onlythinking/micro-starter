package com.believe.command.users.configuration;

import com.believe.command.users.aggregate.UserAggregate;
import com.believe.command.users.aggregate.UserCommandHandler;
import com.believe.command.users.client.UsersQueryServiceClient;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Configuration
public class UserConfiguration {

  @Autowired
  private AxonConfiguration axonConfiguration;

  @Autowired
  private EventBus eventBus;

  @Autowired
  private UsersQueryServiceClient usersQueryClient;

  @Bean
  public UserCommandHandler userCommandHandler() {
    return new UserCommandHandler(axonConfiguration.repository(UserAggregate.class), eventBus, usersQueryClient);
  }
}
