package com.believe.command.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p> </p>
 *
 * @author Li Xingping
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

  public static void main(String... args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(Application.class);
    Environment env = app.run(args).getEnvironment();
    log.info("\n----------------------------------------------------------\n\t" +
        "Application '{}' is running! Access URLs:\n\t" +
        "Local: \t\thttp://127.0.0.1:{}\n\t" +
        "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty("spring.application.name"),
      env.getProperty("server.port"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"));
  }
}