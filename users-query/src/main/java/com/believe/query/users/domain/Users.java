package com.believe.query.users.domain;

import com.believe.commons.query.model.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * <p> The users entity </p>
 *
 * @author Li Xingping
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@ToString
public class Users extends Domain<String> {

  @NotBlank
  @Column(unique = true, nullable = false)
  private String username;
}
