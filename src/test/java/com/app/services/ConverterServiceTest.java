package com.app.services;

import com.app.entities.UserRequestEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterServiceTest {
    @Test
    void processCheckTesting() {
        UserRequestEntity request = new UserRequestEntity((double) 12, "inches", "meters");
        ConverterService service = new ConverterService();

        assertEquals(service.processCheck(request), 1);
        request.setDestination("kilometers");
        assertEquals(service.processCheck(request), -1);
        request.setDestination("meters");
        request.setNumber((double) -10);
        assertEquals(service.processCheck(request), -2);
    }
}