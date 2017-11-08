package com.believe.query.users.web;

import com.believe.api.users.domain.User;
import com.believe.query.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RestController
public class UsersQueryController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/list")
  public List<User> list() {
    return userRepository.findAll();
  }

}

