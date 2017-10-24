package com.believe.command.users.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Rabbit Configuration </p>
 *
 * @author Li Xingping
 */
@Configuration
public class RabbitConfiguration {

  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.username}")
  private String username;

  @Value("${spring.rabbitmq.password}")
  private String password;

  @Value("${spring.application.exchange}")
  private String exchangeName;

  @Value("${spring.application.queue}")
  private String queueName;

  @Bean
  FanoutExchange eventBusExchange() {
    return new FanoutExchange(exchangeName, true, false);
  }

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    return connectionFactory;
  }

  @Autowired
  void rabbitAdmin(AmqpAdmin admin, FanoutExchange eventBusExchange) {
    admin.declareExchange(eventBusExchange);
  }

}
