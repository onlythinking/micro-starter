package com.believe.documentation.clients;

import com.believe.api.users.domain.User;
import com.believe.command.users.client.UsersQueryServiceClient;
import com.believe.query.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Service
public class UsersQueryServiceClientImpl implements UsersQueryServiceClient {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Resource<User> findOneUsers(String userId) {
    return new Resource<>(
      userRepository.findOneById(userId).get()
    );
  }

  @Override
  public Resource<User> findByUsername(String username) {
    return new Resource<>(
      userRepository.findByUsername(username)
    );
  }
}
