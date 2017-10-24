package com.believe.query.users.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Data
@Entity
@Table(name = "users")
public class Users {

  @Id
  @GeneratedValue
  private Long id;

  private Long version;

  @Column(unique = true, nullable = false)
  private String username;
}
