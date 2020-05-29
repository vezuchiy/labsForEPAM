package com.app.repositories;

import com.app.models.UserRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {
    Iterable<UserRequest> findAll();
    UserRequest findByNumberAndSourceAndDestination(Double number, String source, String destination);
}
