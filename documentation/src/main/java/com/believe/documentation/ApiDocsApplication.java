package com.believe.documentation;


import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

/**
 * <p> Documentation service </p>
 *
 * @author Li Xingping
 */

@Configuration
@ComponentScan("com.believe")
@EnableJpaRepositories("com.believe.query.users.repository")
@EntityScan({"org.axonframework", "com.believe.api.users.domain"})
@SpringBootApplication
public class ApiDocsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiDocsApplication.class, args);
  }

  @Bean
  public Module jacksonAfterBurnerModule() {
    return new AfterburnerModule();
  }

  @Bean
  public HttpMessageConverter httpSmileJackson2MessageConverter() {
    return new AbstractJackson2HttpMessageConverter(
      new ObjectMapper(new SmileFactory()).registerModule(new AfterburnerModule()),
      new MediaType("application", "x-jackson-smile")) {
    };
  }
}
