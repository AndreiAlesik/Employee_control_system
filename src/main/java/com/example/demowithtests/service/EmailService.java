package com.example.demowithtests.service;


import com.example.demowithtests.domain.EmailDetails;
import com.example.demowithtests.domain.Employee;

import java.util.List;

// Interface
public interface EmailService {

    void sendSimpleMail(EmailDetails details, String city);

    void sendMailWithAttachment(EmailDetails details, String city);

}