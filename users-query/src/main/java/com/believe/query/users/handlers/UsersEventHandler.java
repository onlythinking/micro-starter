package com.believe.query.users.handlers;

import com.believe.api.users.event.UsersCreatedEvent;
import com.believe.query.users.domain.Users;
import com.believe.query.users.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Slf4j
@Component
@ProcessingGroup("users")
public class UsersEventHandler {

  @Autowired
  private UsersRepository usersRepository;

  @EventHandler
  public void handle(UsersCreatedEvent event, @SequenceNumber Long version) {
    log.info("UsersCreatedEvent: [{}] ", event.getId());
    Users users = new Users();
    users.setUsername(event.getUsername());
    users.setVersion(version);
    usersRepository.save(users);
  }

}
