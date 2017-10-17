package com.believe.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p> Api gateway service </p>
 *
 * @author Li Xingping
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
//@EnableResourceServer
@EnableHystrix
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
