package com.believe.api.users.event;

import com.believe.api.users.model.UsersId;
import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class UsersUpdatedEvent implements Serializable {

  private final UsersId id;
  private final String username;

}
