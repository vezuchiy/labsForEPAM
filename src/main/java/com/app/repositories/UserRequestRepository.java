package com.app.repositories;

import com.app.entities.UserRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends CrudRepository<UserRequestEntity, Long> {
    Iterable<UserRequestEntity> findAll();
    UserRequestEntity findByNumberAndSourceAndDestination(Double number, String source, String destination);
    UserRequestEntity findTopByOrderByIdDesc();
}
