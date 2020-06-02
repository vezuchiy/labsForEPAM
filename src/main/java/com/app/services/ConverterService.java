package com.app.services;

import com.app.entities.ServiceResponseEntity;
import com.app.entities.UserRequestEntity;

public class ConverterService {
    private Double coefficient = 39.371;

    public int processCheck(UserRequestEntity request) {
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

    public ServiceResponseEntity processConvert(UserRequestEntity request) { // main calculations
        ServiceResponseEntity response = new ServiceResponseEntity();
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
