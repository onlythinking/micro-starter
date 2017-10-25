package com.believe.commons.query.repository;

import com.believe.commons.query.model.Record;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <p> Basic record repository </p>
 *
 * @author Li Xingping
 */
@NoRepositoryBean
public interface RecordRepository<T extends Record> extends ModelRepository<T, Long> {
}
