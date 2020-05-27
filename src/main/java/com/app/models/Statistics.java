package com.app.models;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {
    private int generalNumber;
    private int validNumber;
    private int uniqueNumber;
    private double minValue;
    private double maxValue;
    private double popularNumber;

    public Statistics() {
        this.generalNumber = this.uniqueNumber = this.validNumber = 0;
        this.minValue = this.maxValue = this.popularNumber = 0;
    }

    public void processList(ArrayList<ServiceResponse> responses) {
        responses.stream().forEach(serviceResponse -> {
                     if(serviceResponse.getResultNumber() > this.maxValue) {
                         this.maxValue = serviceResponse.getResultNumber();
                     }
                     else if(serviceResponse.getResultNumber() < this.maxValue) {
                         this.minValue = serviceResponse.getResultNumber();
                     }
                 });
        int popularCounter = 0;
        for (ServiceResponse response : responses) {
            int currentCounter = Collections.frequency(responses, response);
            if(currentCounter > popularCounter) {
                popularCounter = currentCounter;
                this.popularNumber = response.getResultNumber();
            }
        }
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getPopularNumber() {
        return popularNumber;
    }

    public void setGeneralNumber(int generalNumber) {
        this.generalNumber = generalNumber;
    }

    public void setPopularNumber(double popularNumber) {
        this.popularNumber = popularNumber;
    }

    public int getGeneralNumber() {
        return generalNumber;
    }

    public int getValidNumber() {
        return validNumber;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public void incUniqueNumber() {
        this.uniqueNumber++;
    }

    public void incValidNumber() {
        this.validNumber++;
    }
}
