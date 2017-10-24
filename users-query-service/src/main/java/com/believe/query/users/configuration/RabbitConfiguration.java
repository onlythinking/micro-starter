package com.believe.query.users.configuration;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
  Queue eventStream() {
    return new Queue(queueName, true);
  }

  @Bean
  FanoutExchange eventBusExchange() {
    return new FanoutExchange(exchangeName, true, false);
  }

  @Bean
  Binding binding() {
    return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, "*.*", null);
  }

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    return connectionFactory;
  }

  @Autowired
  void rabbitAdmin(AmqpAdmin admin, FanoutExchange eventBusExchange, Queue defaultStream, Binding binding) {
    admin.declareExchange(eventBusExchange);
    admin.declareQueue(defaultStream);
    admin.declareBinding(binding);
  }

  @Bean("jacksonSerializer")
  Serializer serializer() {
    return new JacksonSerializer();
  }

  @Bean(name = "messageSourceUsers")
  public SpringAMQPMessageSource messageSourceUsers(@Qualifier("jacksonSerializer") Serializer serializer) {
    return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

      @RabbitListener(queues = "${spring.application.queue}")
      @Override
      public void onMessage(Message message, Channel channel) throws Exception {
        super.onMessage(message, channel);
      }
    };
  }
}
