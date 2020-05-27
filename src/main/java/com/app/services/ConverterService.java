package com.app.services;

import com.app.models.ServiceResponse;
import com.app.models.UserRequest;

public class ConverterService {
    private Double coefficient = 39.371;

    public int processCheck(UserRequest request) {
        if(request.getNumber() == null) {
            return -1; // Bad parameters
        }
        if(request.getNumber() < 0) {
            return -2; // Internal error: incorrect calculations result
        }
        if((!request.getSource().equals("meters") && !request.getSource().equals("inches")) ||
           (!request.getDestination().equals("meters") && !request.getDestination().equals("inches"))) {
            return -1; // Bad parameters
        }
        return 1;
    }

    public ServiceResponse processConvert(UserRequest request) { // main calculations
        ServiceResponse response = new ServiceResponse();
        if(request.getSource().equals("meters") && request.getDestination().equals("inches")) {
            response.setComment("meters -> inches");
            response.setResultNumber(request.getNumber() * this.coefficient);
        }
        else {
            response.setComment("inches -> meters");
            response.setResultNumber(request.getNumber() / this.coefficient);
        }
        return response;
    }
}
