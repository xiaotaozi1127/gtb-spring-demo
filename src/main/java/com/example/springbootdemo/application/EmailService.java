package com.example.springbootdemo.application;

import com.example.springbootdemo.userInterface.dto.request.EmailRequest;
import com.example.springbootdemo.userInterface.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse getEmail(EmailRequest emailRequest);
}
