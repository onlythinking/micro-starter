package com.believe.command.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import javax.annotation.PostConstruct;
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
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
@EnableFeignClients
public class Application {

  @Autowired
  private ObjectMapper objectMapper;

  @PostConstruct
  public void registerModule(){
    objectMapper.registerModule(new JavaTimeModule());
  }

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