package com.believe.commons.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Getter
@EqualsAndHashCode
public abstract class IdIdentifier implements Serializable {

  @NotBlank
  protected final String value;

  protected IdIdentifier() {
    this.value = id();
  }

  protected IdIdentifier(String value) {
    this.value = value;
  }

  protected static String id() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  @Override
  public String toString() {
    return this.value;
  }
}
