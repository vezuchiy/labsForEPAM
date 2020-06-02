package com.app.controller;


import com.app.entities.ServiceResponseEntity;
import com.app.entities.StatisticEntity;
import com.app.entities.UserRequestEntity;
import com.app.exceptions.BadRequestError;
import com.app.exceptions.InternalServiceError;
import com.app.models.*;
import com.app.repositories.ServiceResponseRepository;
import com.app.repositories.StatisticRepository;
import com.app.repositories.UserRequestRepository;
import com.app.services.ConverterService;

import com.app.services.StatisticProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;


@RestController
@RequestMapping(value = "/converter")
public class Controller {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private ServiceResponseRepository serviceResponseRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    CallCounter counter = new CallCounter(0, new ReentrantLock());
    ConverterService converterService = new ConverterService();
    Logger logger = LoggerFactory.getLogger(this.getClass());
    StatisticProcessService statisticProcessService = new StatisticProcessService();

    @GetMapping("/process/{id}")
    public @ResponseBody StatisticEntity getStatisticById(@PathVariable("id")Integer id){
        return statisticRepository.findByProcessId(id);
    }

    @Async("myExecutor")
    @PostMapping(value = "/postRequestsList")
    public CompletableFuture<StatisticEntity> processRequestsList(@RequestBody UserRequestsList requests) {

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setTotalCount(requests.getRequests().size());
        int processId = userRequestRepository.findTopByOrderByIdDesc().getId() + 1;
        statisticEntity.setProcessId(processId);

        CompletableFuture.runAsync(() -> {
            ArrayList<ServiceResponseEntity> validList = new ArrayList<>();
            requests.getRequests()
                    .stream()
                    .forEach(request -> {
                        if(this.converterService.processCheck(request) > 0) {
                            statisticEntity.incValidNumber();
                            ServiceResponseEntity response = this.converterService.processConvert(request);
                            UserRequestEntity findRequest = userRequestRepository
                                    .findByNumberAndSourceAndDestination(request.getNumber(), request.getSource(), request.getDestination());
                            if(findRequest == null) {
                                statisticEntity.incUniqueNumber();
                                userRequestRepository.save(request);
                                serviceResponseRepository.save(response);
                            }
                            validList.add(response);
                        }
                    });
            this.statisticProcessService.setStatisticEntity(statisticEntity);
            this.statisticProcessService.processList(validList);
            statisticRepository.save(statisticEntity);
        });

        return CompletableFuture.completedFuture(statisticEntity);
    }

    @GetMapping(value = "/processGet")
    public CompletableFuture<ServiceResponseEntity> processRequest(@RequestParam(value = "number") Double number,
                                                                   @RequestParam(value = "from") String source,
                                                                   @RequestParam(value = "to") String destination) throws BadRequestError, InternalServiceError {
        UserRequestEntity request = new UserRequestEntity(number, source, destination);
        switch(this.converterService.processCheck(request)) {
            case -1: throw new BadRequestError(400, "Incorrect input parameters.");
            case -2: throw new InternalServiceError(500, "Internal service error.");
            case 1:{
                logger.info("Verification was successful.");
                break;
            }
        }
        UserRequestEntity findRequest = userRequestRepository.findByNumberAndSourceAndDestination(number, source, destination);
        if(findRequest == null) {
            userRequestRepository.save(request);
            ServiceResponseEntity response = this.converterService.processConvert(request);
            serviceResponseRepository.save(response);
            return CompletableFuture.completedFuture(response);
        }
        return CompletableFuture.completedFuture(serviceResponseRepository.findById(request.getId()));
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<UserRequestEntity> getAll() {
        return this.userRequestRepository.findAll();
    }

    @GetMapping(value = "/counter")
    public Integer getCounter() {
        return counter.getCallsCount();
    }

    @GetMapping(value = "/counterToZero")
    public void nullifyCounter() {
        counter.nullifyCallsCount();
    }
}
