package com.believe.api.users.model;

import com.believe.commons.api.IdIdentifier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialId extends IdIdentifier {
  public SocialId(String value) {
    super(value);
  }
}
