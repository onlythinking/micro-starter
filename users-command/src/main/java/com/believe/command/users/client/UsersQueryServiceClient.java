package com.believe.command.users.client;

import com.believe.api.users.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@FeignClient(name = "query-users", decode404 = true)
public interface UsersQueryServiceClient {

  @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Resource<User> findOneUsers(@PathVariable("id") String userId);

  @RequestMapping(method = RequestMethod.GET, value = "/users/search/username", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Resource<User> findByUsername(@RequestParam("u") String username);

}
