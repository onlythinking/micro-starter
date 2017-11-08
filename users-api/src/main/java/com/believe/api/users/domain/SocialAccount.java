package com.believe.api.users.domain;

import com.believe.api.users.model.SocialAccountType;
import com.believe.commons.api.domain.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "social_account")
@EqualsAndHashCode(callSuper = true, exclude = {"owner"})
@ToString(exclude = {"owner"})
public class SocialAccount extends Model<String> {

  @NotBlank
  @Column(nullable = false)
  private String accountNo;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SocialAccountType socialType;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "owner_id", nullable = false, updatable = false)
  private User owner;

}
