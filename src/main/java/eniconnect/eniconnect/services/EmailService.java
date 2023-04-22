package eniconnect.eniconnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("eniconnect2023@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        System.out.println("Mail sent successfully");
    }
}

