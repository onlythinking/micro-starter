package com.believe.documentation;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;

import static springfox.documentation.builders.PathSelectors.ant;

/**
 * @author Li Xingping
 */
@SuppressWarnings("unchecked")
@Configuration
@EnableSwagger2
@Import({
  BeanValidatorPluginsConfiguration.class
})
public class SwaggerConfiguration {

  private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

  public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

  @Bean
  public Docket restApi() {
    log.debug("Starting Swagger");
    StopWatch watch = new StopWatch();
    watch.start();
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
      .produces(Sets.newHashSet("application/json;charset=UTF-8"))
      .consumes(Sets.newHashSet("application/json;charset=UTF-8"))
      .protocols(Sets.newHashSet("http", "https"))
      .apiInfo(apiInfo())
      .forCodeGeneration(true)
      .genericModelSubstitutes(ResponseEntity.class)
      .ignoredParameterTypes(Pageable.class)
      .ignoredParameterTypes(java.sql.Date.class)
      .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
      .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
      .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//      .groupName("users-api")
//      .securitySchemes(asList(
//        new OAuth(
//          "petstore_auth",
//          asList(new AuthorizationScope("write_pets", "modify pets in your account"),
//            new AuthorizationScope("read_pets", "read your pets")),
//          Arrays.<GrantType>asList(new ImplicitGrant(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog"), "tokenName"))
//        ),
//        new ApiKey("api_key", "api_key", "header")
//      ))
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(
        Predicates.and(ant("/**"),
          Predicates.not(ant("/error")),
          Predicates.not(ant("/management/**")),
          Predicates.not(ant("/management*"))))
      .build();
    watch.stop();
    log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Micro starter API Docs")
      .description("Micro starter API Docs.")
      .contact(new Contact("Li Xingping", "http:/127.0.0.1:8080", "lixingping233@gmail.com"))
      .license("Apache 2.0")
      .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
      .version("1.0")
      .build();
  }
}
