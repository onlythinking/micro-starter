package com.believe.command.users.configuration;

import com.believe.command.users.aggregate.UsersAggregate;
import com.believe.command.users.aggregate.UsersCommandHandler;
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
public class UsersConfiguration {

  @Autowired
  private AxonConfiguration axonConfiguration;

  @Autowired
  private EventBus eventBus;

  @Bean
  public UsersCommandHandler usersCommandHandler() {
    return new UsersCommandHandler(axonConfiguration.repository(UsersAggregate.class), eventBus);
  }
}
