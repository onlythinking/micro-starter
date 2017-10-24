package com.believe.query.users.handlers;

import com.believe.api.users.event.UsersCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
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

  @EventHandler
  public void handle(UsersCreatedEvent event, @SequenceNumber Long version) {
    log.info("UsersCreatedEvent: [{}] ", event.getId());
  }

}
