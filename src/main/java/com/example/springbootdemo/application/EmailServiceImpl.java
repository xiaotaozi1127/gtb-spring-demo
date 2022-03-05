package com.example.springbootdemo.application;

import com.example.springbootdemo.userInterface.dto.request.EmailRequest;
import com.example.springbootdemo.userInterface.dto.response.EmailResponse;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public EmailResponse getEmail(EmailRequest emailRequest) {
        return new EmailResponse(emailRequest.getName(), emailRequest.getName() + "@tw.com");
    }
}
