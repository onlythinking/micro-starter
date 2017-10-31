package com.believe.commons.query.repository;

import com.believe.commons.query.model.Domain;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p> Basic domain repository </p>
 *
 * @author Li Xingping
 */
@NoRepositoryBean
public interface DomainRepository<T extends Domain> extends ModelRepository<T, String> {

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  <S extends T> S save(S entity);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  <S extends T> List<S> save(Iterable<S> entities);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  <S extends T> S saveAndFlush(S entity);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  void delete(String id);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  void delete(T entity);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  void deleteInBatch(Iterable<T> entities);

  @ApiIgnore
  @Override
  @RestResource(exported = false)
  void deleteAllInBatch();

}
