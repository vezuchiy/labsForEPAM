package com.app.services;

import com.app.entities.ServiceResponseEntity;
import com.app.entities.StatisticEntity;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticProcessService {

    private StatisticEntity statisticEntity;

    public StatisticProcessService() {}

    public void processList(ArrayList<ServiceResponseEntity> responses) {
        boolean flagForMin = false, flagForMax = false;
        int popularCounter = 0;
        for (ServiceResponseEntity response : responses) {
            if(!flagForMax) {
                flagForMax = true;
                this.statisticEntity.setMaxValue(response.getResultNumber());
            } else if(this.statisticEntity.getMaxValue() < response.getResultNumber()) {
                this.statisticEntity.setMaxValue(response.getResultNumber());
            }
            if(!flagForMin) {
                flagForMin = true;
                this.statisticEntity.setMinValue(response.getResultNumber());
            } else if(this.statisticEntity.getMinValue() > response.getResultNumber()) {
                this.statisticEntity.setMinValue(response.getResultNumber());
            }
            int currentCounter = Collections.frequency(responses, response);
            if(currentCounter > popularCounter) {
                popularCounter = currentCounter;
                this.statisticEntity.setPopularValue(response.getResultNumber());
            }
        }
    }

    public void setStatisticEntity(StatisticEntity statisticEntity) {
        this.statisticEntity = statisticEntity;
    }
}
