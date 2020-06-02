package com.app.repositories;

import com.app.entities.StatisticEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {
    StatisticEntity findByProcessId(Integer id);
}
