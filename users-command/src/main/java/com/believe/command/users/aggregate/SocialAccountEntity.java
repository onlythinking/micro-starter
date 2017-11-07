package com.believe.command.users.aggregate;

import com.believe.api.users.model.SocialId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
class SocialAccountEntity implements Serializable {

  @NotNull
  private SocialId socialId;
  private boolean authenticated;
  private String token;
  private String data;
}
