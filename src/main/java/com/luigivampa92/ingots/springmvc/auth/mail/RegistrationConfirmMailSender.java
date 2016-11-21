package com.luigivampa92.ingots.springmvc.auth.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConfirmMailSender {

    private static final String SUBJECT = "Confirm your registration";
    private static final String HEADER = "Please confirm your registration by visiting link below:";

    @Value("${email.sender.account}")
    private String emailAccount;

    @Autowired
    private MailSender mailSender;

    @Async
    public void sendEmail(String reciever, String confirmUrl) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailAccount);
        message.setTo(reciever);
        message.setSubject(SUBJECT);
        message.setText(HEADER + "\n\n" + confirmUrl);
        mailSender.send(message);
    }
}
