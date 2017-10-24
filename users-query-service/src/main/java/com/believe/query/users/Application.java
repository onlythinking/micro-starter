package com.believe.query.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> </p>
 *
 * @author Li Xingping
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class Application {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}