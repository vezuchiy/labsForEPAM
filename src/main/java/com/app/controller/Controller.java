package com.app.controller;


import com.app.exceptions.BadRequestError;
import com.app.exceptions.InternalServiceError;
import com.app.models.*;
import com.app.repositories.UserRequestRepository;
import com.app.services.CacheService;
import com.app.services.ConverterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


@RestController
@RequestMapping(value = "/converter")
public class Controller {

    @Autowired
    private UserRequestRepository userRequestRepository;

    CallCounter counter = new CallCounter(0, new ReentrantLock());
    Logger logger = LoggerFactory.getLogger(this.getClass());
    CacheService cache = new CacheService();
    ConverterService converterService = new ConverterService();

    @GetMapping("/getAll")
    public @ResponseBody Iterable<UserRequest> getAll() {
        return this.userRequestRepository.findAll();
    }

    @GetMapping(value = "/processGet")
    public ServiceResponse processRequest(@RequestParam(value = "number") Double number,
                                          @RequestParam(value = "from") String source,
                                          @RequestParam(value = "to") String destination) throws BadRequestError, InternalServiceError {
        UserRequest request = new UserRequest(number, source, destination);
        switch(this.converterService.processCheck(request)) {
            case -1: throw new BadRequestError(400, "Incorrect input parameters.");
            case -2: throw new InternalServiceError(500, "Internal service error.");
            case 1:{
                logger.info("Verification was successful.");
                break;
            }
        }
        counter.incrementCounter();
        return this.cache.getCache()
                   .entrySet()
                   .stream()
                   .filter(entry -> entry.getKey().equals(request))
                   .findAny()
                   .map(Map.Entry::getValue)
                   .orElseGet(() -> {
                       ServiceResponse response = this.converterService.processConvert(request);
                       this.cache.add(request, response);
                       this.userRequestRepository.save(request);
                       return response;
                   });
    }

    @PostMapping(value = "postRequestsList")
    public Statistics processRequestsList(@RequestBody UserRequestsList requests) {
        Statistics statistics = new Statistics();
        statistics.setGeneralNumber(requests.getRequests().size());

        ArrayList<ServiceResponse> validList = new ArrayList<>();

        requests.getRequests()
                .stream()
                .forEach(request -> {
                    if(this.converterService.processCheck(request) > 0) {
                        statistics.incValidNumber();
                        ServiceResponse response = this.converterService.processConvert(request);
                        if(!this.cache.find(request)) {
                            statistics.incUniqueNumber();
                            this.cache.add(request, response);
                            this.userRequestRepository.save(request);
                        }
                        validList.add(response);
                    }
                });
        statistics.processList(validList);
        return statistics;
    }

    @GetMapping(value = "/counter")
    public Integer getCounter() {
        return counter.getCallsCount();
    }

    @GetMapping(value = "/cache")
    public Map<UserRequest, ServiceResponse> getCache() {
        return this.cache.getCache();
    }

    @GetMapping(value = "/counterToZero")
    public void nullifyCounter() {
        counter.nullifyCallsCount();
    }
}
