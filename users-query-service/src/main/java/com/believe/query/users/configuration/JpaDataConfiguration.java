package com.believe.query.users.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Configuration
@EnableJpaRepositories("com.believe.query.users.repository")
@EntityScan({"org.axonframework", "com.believe.query.users.domain"})
public class JpaDataConfiguration {
}
