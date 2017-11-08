package com.believe.commons.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * <p> Basic record entity </p>
 *
 * @author Li Xingping
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Record<T extends Serializable> extends Model<T> {

  @Column
  private String comment;

}
