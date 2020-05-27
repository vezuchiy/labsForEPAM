package com.app.models;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsList {
    private List<UserRequest> requests = new ArrayList<>();

    public UserRequestsList() {}

    public List<UserRequest> getRequests() {
        return this.requests;
    }

    public void setRequests(List<UserRequest> requests) {
        this.requests = requests;
    }
}
