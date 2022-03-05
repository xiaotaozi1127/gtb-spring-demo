package com.example.springbootdemo.userInterface.dto.response;

public class EmailResponse {
    public EmailResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private final String name;
    private final String email;
}
