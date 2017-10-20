package com.believe.api.users.event;

import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class UsersCreatedEvent implements Serializable {

  private final String id;
  private final String username;

}
