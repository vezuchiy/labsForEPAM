package com.app.repositories;

import com.app.models.UserRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {
    @Async
    Iterable<UserRequest> findAll();
    @Async
    UserRequest findByNumberAndSourceAndDestination(Double number, String source, String destination);
}
