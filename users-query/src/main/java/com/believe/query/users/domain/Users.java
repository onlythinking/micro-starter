package com.believe.query.users.domain;

import com.believe.api.users.model.SocialAccountType;
import com.believe.commons.query.model.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> The users entity </p>
 *
 * @author Li Xingping
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true, exclude = {"socialAccounts"})
@ToString(exclude = {"socialAccounts"})
public class Users extends Domain<String> {

  @NotBlank
  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private boolean activated = true;

  @Column
  private String password;

  @Setter(AccessLevel.NONE)
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private Set<SocialAccount> socialAccounts = Sets.newHashSet();

  public Users bindSocialAccount(String socialId, String accountNo, SocialAccountType socialAccountType) {
    SocialAccount socialAccount = new SocialAccount();
    socialAccount.setId(socialId);
    socialAccount.setAccountNo(accountNo);
    socialAccount.setSocialType(socialAccountType);
    socialAccount.setOwner(this);
    socialAccount.setCreatedDate(Instant.now());
    this.socialAccounts.add(socialAccount);
    return this;
  }

  public Users unbindSocialAccount(String socialId) {
    this.socialAccounts = this.socialAccounts.stream().filter(socialAccount -> !socialAccount.getId().equals(socialId)).collect(Collectors.toSet());
    return this;
  }
}
