package com.app.models;

public class ServiceResponse {

    private Double requestNumber;
    private Double resultNumber;
    private String  finalComment;

    public ServiceResponse() {}

    public ServiceResponse(Double value) {
        this.requestNumber = value;
    }

    public void convert(String from, String to) {
        if(from.equals("inches") && to.equals("meters")) {
            this.resultNumber = this.requestNumber / 39.371;
            this.finalComment = "inches -> meters";
        }
        else if(from.equals("meters") && to.equals("inches")) {
            this.resultNumber = this.requestNumber * 39.371;
            this.finalComment = "meters->inches";
        }
        else {
            this.requestNumber = null;
            this.finalComment = "error input parameters!!!!!!";
        }
    }

    public void setRequestNumber(Double requestNumber) {
        this.requestNumber = requestNumber;
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

    public String getComment(String comment) {
        return this.finalComment;
    }
}
