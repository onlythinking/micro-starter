package com.believe.api.users.event;

import com.believe.api.users.model.UserId;
import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class UserDisActivatedEvent implements Serializable {
  private final UserId identifier;
  private final String username;
  private final boolean activated;
}
