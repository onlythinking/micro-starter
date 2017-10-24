package com.believe.query.users.repository;

import com.believe.query.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
}
