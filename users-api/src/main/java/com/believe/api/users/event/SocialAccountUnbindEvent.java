package com.believe.api.users.event;

import com.believe.api.users.model.SocialAccountType;
import com.believe.api.users.model.SocialId;
import com.believe.api.users.model.UserId;
import lombok.Value;

import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Value
public class SocialAccountUnbindEvent implements Serializable {
  private final UserId identifier;
  private final SocialId socialId;
  private final SocialAccountType socialAccountType;
  private final String accountNo;
}
