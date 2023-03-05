package com.example.demowithtests.service;

import com.example.demowithtests.domain.EmailDetails;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class EmailServiceBean implements EmailService {

    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    private final Repository repository;

    public EmailServiceBean(JavaMailSender javaMailSender, Repository repository) {
        this.javaMailSender = javaMailSender;
        this.repository = repository;
    }


    public void sendSimpleMail(EmailDetails details, String city) {
        try {
            mailProcessor(details, city);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMailWithAttachment(EmailDetails details, String city) {
        try {
            mailProcessorWithAttachment(details, city);
        } catch (MessagingException e) {
            log.info("Error while sending mail!!!");
        }
    }

    public List<String> getEmailsList(String city) {
        List<Employee> employees = repository.findEmployeeByAddressesSQL(city);
        System.out.println(employees);
        List<String> emails = new ArrayList<>();
        for (Employee emp : employees) {
            emails.add(emp.getEmail());
        }
        return emails;
    }

    private void mailProcessor(EmailDetails details, String city) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());
        for (String tmp : getEmailsList(city)) {
            mailMessage.setTo(tmp);

            javaMailSender.send(mailMessage);
        }
        log.info("Emails were successfully sent");
    }

    private void mailProcessorWithAttachment(EmailDetails details, String city) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setText(details.getMsgBody());
        mimeMessageHelper.setSubject(details.getSubject());
        FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
        mimeMessageHelper.addAttachment(file.getFilename(), file);
        for (String tmp : getEmailsList(city)) {
            mimeMessageHelper.setTo(tmp);
            javaMailSender.send(mimeMessage);
        }
        System.out.println("Mail sent Successfully");
    }

}