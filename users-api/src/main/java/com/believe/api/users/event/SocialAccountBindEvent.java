package com.believe.api.users.event;

import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.UsersId;
import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class SocialAccountBindEvent implements Serializable {
  private final UsersId identifier;
  private final String username;
  private final SocialAccountType socialAccountType;
  private final String socialAccount;
}
