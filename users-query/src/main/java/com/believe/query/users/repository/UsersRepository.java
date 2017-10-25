package com.believe.query.users.repository;

import com.believe.commons.query.repository.DomainRepository;
import com.believe.query.users.domain.Users;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UsersRepository extends DomainRepository<Users> {
}
