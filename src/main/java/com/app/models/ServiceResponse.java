package com.app.models;


public class ServiceResponse {
    private Double resultNumber;
    private String  finalComment;

    public ServiceResponse() {}

    public ServiceResponse(Double value) {
        this.resultNumber = value;
    }

    public Double getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(Double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public void setComment(String comment) {
        this.finalComment = comment;
    }

    public String getComment() {
        return this.finalComment;
    }
}
