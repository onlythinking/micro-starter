package com.believe.commons.query.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * <p> Basic model entity </p>
 *
 * @author Li Xingping
 */
@MappedSuperclass
@Data
public abstract class Model<ID extends Serializable> implements Serializable {

  @Id
  @NotNull
  private ID id;

  @NotNull
  @Column(name = "created_date", nullable = false, updatable = false)
  private Instant createdDate;

}
