package com.app.models;

import com.app.entities.UserRequestEntity;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsList {
    private List<UserRequestEntity> requests = new ArrayList<>();

    public UserRequestsList() {}

    public List<UserRequestEntity> getRequests() {
        return this.requests;
    }

    public void setRequests(List<UserRequestEntity> requests) {
        this.requests = requests;
    }
}
