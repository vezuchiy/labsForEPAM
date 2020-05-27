package com.app.services;

import com.app.models.ServiceResponse;
import com.app.models.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CacheService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<UserRequest, ServiceResponse> cache = new HashMap<>();

    public CacheService() {}

    public Map<UserRequest, ServiceResponse> getCache() {
        return this.cache;
    }

    public boolean find(UserRequest request) {
        if (this.cache.containsKey(request)) return true;
        else return false;
    }

    public void add(UserRequest request, ServiceResponse serviceResponse) {
        logger.info("Save new request in the cache");
        this.cache.put(request, serviceResponse);
    }

    public ServiceResponse getResponse(UserRequest request) {
        return this.cache.get(request);
    }
}
