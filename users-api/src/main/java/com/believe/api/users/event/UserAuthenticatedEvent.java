package com.believe.api.users.event;

import com.believe.api.users.model.UsersId;
import lombok.Value;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class UserAuthenticatedEvent {
  private final UsersId identifier;
}
