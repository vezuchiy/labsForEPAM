package com.app.controller;

import com.app.models.ServiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/converter")
public class Controller {
    @GetMapping(value = "process")
    public ServiceResponse processRequest(@RequestParam(value = "number") Double number,
                                          @RequestParam(value = "from") String from,
                                          @RequestParam(value = "to") String to) {
        ServiceResponse response = new ServiceResponse(number);
        response.convert(from, to);
        return response;
    }
}
