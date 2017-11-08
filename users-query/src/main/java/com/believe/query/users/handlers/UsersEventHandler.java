package com.believe.query.users.handlers;

import com.believe.api.users.event.*;
import com.believe.api.users.domain.User;
import com.believe.query.users.repository.UserRepository;
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
  private UserRepository userRepository;

  @EventHandler
  public void handle(final UserCreatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UserCreatedEvent: [{}] ", event.getIdentifier());
    User user = new User();
    user.setId(event.getIdentifier().getIdentifier());
    user.setUsername(event.getUsername());
    user.setAggregateVersion(version);

    user.setCreatedDate(timestamp);
    user.setLastModifiedDate(timestamp);
    userRepository.save(user);
  }

  @EventHandler
  @Transactional
  public void handle(final SocialAccountBindEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("SocialAccountBindEvent: [{}] ", event.getIdentifier());
    userRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.bindSocialAccount(event.getSocialId().getIdentifier(), event.getAccountNo(), event.getSocialAccountType());
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      userRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final SocialAccountUnbindEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("SocialAccountUnbindEvent: [{}] ", event.getIdentifier());
    userRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.unbindSocialAccount(event.getSocialId().getIdentifier());
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      userRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final UserActivatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UserActivatedEvent: [{}] ", event.getIdentifier());
    userRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.setActivated(true);
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      userRepository.save(users);
    });
  }

  @EventHandler
  @Transactional
  public void handle(final UserDisActivatedEvent event, @Timestamp Instant timestamp, @SequenceNumber Long version) {
    log.info("UserDisActivatedEvent: [{}] ", event.getIdentifier());
    userRepository.findOneById(event.getIdentifier().getIdentifier()).ifPresent(users -> {
      users.setActivated(false);
      users.setAggregateVersion(version);
      users.setLastModifiedDate(timestamp);
      userRepository.save(users);
    });
  }

}
