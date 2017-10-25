package com.believe.api.users.model;

import com.believe.commons.api.IdIdentifier;
import lombok.*;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UsersId extends IdIdentifier {

  public UsersId(String value) {
    super(value);
  }
}
