package com.believe.query.users.handlers;

import com.believe.api.users.event.UsersCreatedEvent;
import com.believe.api.users.event.UsersUpdatedEvent;
import com.believe.query.users.domain.Users;
import com.believe.query.users.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

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
  public void handle(final UsersCreatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UsersCreatedEvent: [{}] ", event.getId());
    Users users = new Users();
    users.setId(event.getId().getValue());
    users.setUsername(event.getUsername());
    users.setAggregateVersion(version);

    users.setCreatedDate(timestamp);
    users.setLastModifiedDate(timestamp);
    usersRepository.save(users);
  }

  @EventHandler
  public void handle(final UsersUpdatedEvent event, @SequenceNumber Long version) {
    log.info("UsersUpdatedEvent: [{}] ", event.getId());
    usersRepository.findOneById(event.getId().getValue()).ifPresent(users -> {
      users.setUsername(event.getUsername());
      users.setAggregateVersion(version);
      users.updateLastModifiedDate();
      usersRepository.save(users);
    });
  }

}
