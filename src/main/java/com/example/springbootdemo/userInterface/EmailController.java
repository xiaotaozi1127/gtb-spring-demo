package com.example.springbootdemo.userInterface;

import com.example.springbootdemo.application.EmailService;
import com.example.springbootdemo.userInterface.dto.request.EmailRequest;
import com.example.springbootdemo.userInterface.dto.response.EmailResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public EmailResponse getEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.getEmail(emailRequest);
    }
}
