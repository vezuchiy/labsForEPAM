package com.app.repositories;

import com.app.entities.ServiceResponseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceResponseRepository extends CrudRepository<ServiceResponseEntity, Long> {
    Iterable<ServiceResponseEntity> findAll();
    ServiceResponseEntity findById(Integer id);
}
