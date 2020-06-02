package com.app.services;

import com.app.entities.ServiceResponseEntity;
import com.app.entities.UserRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CacheService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<UserRequestEntity, ServiceResponseEntity> cache = new HashMap<>();

    public CacheService() {}

    public Map<UserRequestEntity, ServiceResponseEntity> getCache() {
        return this.cache;
    }

    public boolean find(UserRequestEntity request) {
        if (this.cache.containsKey(request)) return true;
        else return false;
    }

    public void add(UserRequestEntity request, ServiceResponseEntity serviceResponse) {
        logger.info("Save new request in the cache");
        this.cache.put(request, serviceResponse);
    }

    public ServiceResponseEntity getResponse(UserRequestEntity request) {
        return this.cache.get(request);
    }
}
