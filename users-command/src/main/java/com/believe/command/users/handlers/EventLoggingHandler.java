package com.believe.command.users.handlers;

import com.believe.api.users.event.UsersCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Slf4j
@Component
public class EventLoggingHandler {

  @EventHandler
  public void handle(UsersCreatedEvent event) {
    log.debug("Instance:{} EventType:{} EventId:[{}] '{}'", event.getIdentifier().getIdentifier(), event.getClass().getSimpleName(), event.getIdentifier().getIdentifier(), event.getUsername());
  }

}






