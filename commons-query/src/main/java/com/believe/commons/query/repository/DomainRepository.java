package com.believe.commons.query.repository;

import com.believe.commons.query.model.Domain;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * <p> Basic domain repository </p>
 *
 * @author Li Xingping
 */
@NoRepositoryBean
public interface DomainRepository<T extends Domain> extends ModelRepository<T, String> {

  @Override
  @RestResource(exported = false)
  <S extends T> S save(S entity);

  @Override
  @RestResource(exported = false)
  <S extends T> List<S> save(Iterable<S> entities);

  @Override
  @RestResource(exported = false)
  <S extends T> S saveAndFlush(S entity);

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(T entity);

  @Override
  @RestResource(exported = false)
  void deleteInBatch(Iterable<T> entities);

  @Override
  @RestResource(exported = false)
  void deleteAllInBatch();

}
