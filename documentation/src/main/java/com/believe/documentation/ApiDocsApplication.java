package com.believe.documentation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p> Documentation service </p>
 *
 * @author Li Xingping
 */
@ComponentScan(basePackages = {"com.believe.documentation"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApiDocsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiDocsApplication.class, args);
  }

}
