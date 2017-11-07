package com.believe.query.users.handlers;

import com.believe.api.users.event.*;
import com.believe.query.users.domain.Users;
import com.believe.query.users.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    log.info("UsersCreatedEvent: [{}] ", event.getIdentifier());
    Users users = new Users();
    users.setId(event.getIdentifier().getIdentifier());
    users.setUsername(event.getUsername());
    users.setAggregateVersion(version);

    users.setCreatedDate(timestamp);
    users.setLastModifiedDate(timestamp);
    usersRepository.save(users);
  }

  @EventHandler
  @Transactional
  public void handle(final SocialAccountBindEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("SocialAccountBindEvent: [{}] ", event.getIdentifier());
    usersRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.bindSocialAccount(event.getSocialId().getIdentifier(), event.getAccountNo(), event.getSocialAccountType());
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      usersRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final SocialAccountUnbindEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("SocialAccountUnbindEvent: [{}] ", event.getIdentifier());
    usersRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.unbindSocialAccount(event.getSocialId().getIdentifier());
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      usersRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final UsersActivatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UsersActivatedEvent: [{}] ", event.getIdentifier());
    usersRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.setActivated(true);
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      usersRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final UsersDisActivatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UsersDisActivatedEvent: [{}] ", event.getIdentifier());
    usersRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.setActivated(false);
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      usersRepository.save(users);
    });
  }

}
