package com.believe.commons.query.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;

/**
 * <p> Basic domain entity </p>
 *
 * @author Li Xingping
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Domain<ID extends Serializable> extends Model<ID> {

  @Column(nullable = false, name = "last_modified_date")
  private Instant lastModifiedDate;

  @JsonIgnore
  @Version
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private long version;

  @JsonIgnore
  @Column(nullable = false, name = "aggregate_version")
  private long aggregateVersion;

  public void updateLastModifiedDate() {
    this.lastModifiedDate = Instant.now();
  }
}
