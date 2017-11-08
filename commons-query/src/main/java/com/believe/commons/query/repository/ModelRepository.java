package com.believe.commons.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.Optional;

/**
 * <p> Basic Model repository </p>
 *
 * @author Li Xingping
 */
@NoRepositoryBean
public interface ModelRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

  @ApiIgnore
  @RestResource(exported = false)
  Optional<T> findOneById(ID id);
}
