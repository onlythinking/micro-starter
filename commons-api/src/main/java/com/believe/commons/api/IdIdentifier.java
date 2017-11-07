package com.believe.commons.api;

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
public abstract class IdIdentifier implements Serializable {

  @NotBlank
  protected final String identifier;

  protected IdIdentifier() {
    this.identifier = id();
  }

  protected IdIdentifier(String value) {
    this.identifier = value;
  }

  protected static String id() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  @Override
  public int hashCode() {
    return this.identifier.hashCode();
  }

  @Override
  public String toString() {
    return this.identifier;
  }
}
