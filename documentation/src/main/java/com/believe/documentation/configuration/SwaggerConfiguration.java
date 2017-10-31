package com.believe.documentation.configuration;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author Li Xingping
 */
@SuppressWarnings("unchecked")
@Configuration
@EnableSwagger2
@Import({
  BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class
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
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(
        Predicates.and(ant("/**"),
          Predicates.not(ant("/error")),
          Predicates.not(ant("/management/**")),
          Predicates.not(ant("/management*"))
        )
      )
      .build()
      .pathMapping("/")
      .genericModelSubstitutes(ResponseEntity.class)
//      .ignoredParameterTypes(Pageable.class)
      .ignoredParameterTypes(java.sql.Date.class)
      .directModelSubstitute(Instant.class, Date.class)
      .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
      .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
      .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
      .alternateTypeRules(
        newRule(typeResolver.resolve(DeferredResult.class,
          typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
          typeResolver.resolve(WildcardType.class)))
      .useDefaultResponseMessages(false)
      .securitySchemes(newArrayList(apiKey()))
      .securityContexts(newArrayList(securityContext()))
      .enableUrlTemplating(true)
      .tags(new Tag("Users Service", "All apis"));
    watch.stop();
    log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
    return docket;
  }

  @Autowired
  private TypeResolver typeResolver;

  private ApiKey apiKey() {
    return new ApiKey("mykey", "api_key", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
      .securityReferences(defaultAuth())
      .forPaths(PathSelectors.regex("/auth.*"))
      .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope
      = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return newArrayList(
      new SecurityReference("mykey", authorizationScopes));
  }

  @Bean
  SecurityConfiguration security() {
    return new SecurityConfiguration(
      "test-app-client-id",
      "test-app-client-secret",
      "test-app-realm",
      "test-app",
      "apiKey",
      ApiKeyVehicle.HEADER,
      "api_key",
      "," /*scope separator*/);
  }

  @Bean
  UiConfiguration uiConfig() {
    return new UiConfiguration(
      "validatorUrl",// url
      "none",       // docExpansion          => none | list
      "alpha",      // apiSorter             => alpha
      "schema",     // defaultModelRendering => schema
      UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
      false,        // enableJsonEditor      => true | false
      true,         // showRequestHeaders    => true | false
      60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
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
