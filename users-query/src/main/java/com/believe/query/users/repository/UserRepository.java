package com.believe.query.users.repository;

import com.believe.commons.query.repository.DomainRepository;
import com.believe.api.users.domain.User;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RepositoryRestResource(collectionResourceRel = "user")
public interface UserRepository extends DomainRepository<User> {

  @RestResource(path = "/username")
  User findByUsername(@NotBlank
                      @RequestParam("u")
                      @ApiParam(name = "u", required = true) @Param("u") String username);
}
