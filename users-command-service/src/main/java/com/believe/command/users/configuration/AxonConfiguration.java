package com.believe.command.users.configuration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.monitoring.NoOpMessageMonitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
@Configuration
public class AxonConfiguration {

  @Bean
  CommandBus commandBus(TransactionManager transactionManager) {
    SimpleCommandBus commandBus = new SimpleCommandBus(transactionManager, NoOpMessageMonitor.INSTANCE);
    commandBus.registerDispatchInterceptor(new BeanValidationInterceptor());
    return commandBus;
  }
}
