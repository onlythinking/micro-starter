package com.believe.command.users.configuration;

import com.mongodb.MongoClient;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p> Users aggregate entity </p>
 *
 * @author Li Xingping
 */
@Configuration
public class AxonConfiguration {

  @Autowired
  public MongoClient mongoClient;

  @Autowired
  @Qualifier("rabbitTransactionManager")
  public PlatformTransactionManager transactionManager;

  @Value("${spring.application.queue}")
  private String queueName;

  @Value("${spring.application.exchange}")
  private String exchangeName;

  @Value("${spring.application.databaseName}")
  private String databaseName;

  @Value("${spring.application.eventsCollectionName}")
  private String eventsCollectionName;

  @Value("${spring.application.snapshotCollectionName}")
  private String snapshotCollectionName;

//  @Bean
//  XStreamSerializer xmlSerializer() {
//    return new XStreamSerializer();
//  }

//  @Bean
//  JacksonSerializer axonJsonSerializer() {
//    return new JacksonSerializer();
//  }

  @Autowired
  public void configure(@Qualifier("localSegment") SimpleCommandBus simpleCommandBus) {
    simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
  }

  @Bean(name = "axonMongoTemplate")
  MongoTemplate axonMongoTemplate() {
    MongoTemplate template = new DefaultMongoTemplate(mongoClient, databaseName, eventsCollectionName, snapshotCollectionName);
    return template;
  }

  @Bean(name = "axonMongoEventStorageEngine")
  MongoEventStorageEngine mongoEventStorageEngine() {
    return new MongoEventStorageEngine(axonMongoTemplate());
  }

}
