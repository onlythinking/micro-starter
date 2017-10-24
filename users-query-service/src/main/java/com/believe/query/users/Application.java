package com.believe.query.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p> </p>
 *
 * @author Li Xingping
 */
@EnableJpaRepositories("com.believe.query.users.repository")
@EntityScan({"org.axonframework", "com.believe.query.users.domain"})
@SpringBootApplication
//@EnableDiscoveryClient
public class Application {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}