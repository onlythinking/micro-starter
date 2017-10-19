package com.believe.api.users.event;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */

public class UsersCreatedEvent implements Serializable {

  private final String id;
  private final String username;

  public UsersCreatedEvent(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

}
