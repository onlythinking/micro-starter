package com.believe.api.users.event;

import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.SocialId;
import com.believe.api.users.model.UsersId;
import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class SocialAccountUnbindEvent implements Serializable {
  private final UsersId identifier;
  private final SocialId socialId;
  private final SocialAccountType socialAccountType;
  private final String accountNo;
}
