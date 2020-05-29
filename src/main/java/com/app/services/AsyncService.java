package com.app.services;

import com.app.models.UserRequest;
import com.app.repositories.UserRequestRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;

@Configuration
@EnableAsync
public class AsyncService {
//    private RestTemplate restTemplate;
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    private UserRequestRepository userRequestRepository;

    public AsyncService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }
}
