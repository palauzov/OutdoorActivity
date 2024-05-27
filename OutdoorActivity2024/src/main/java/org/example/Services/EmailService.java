package org.example.Services;


import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
import java.util.Scanner;

public class EmailService {
    private static final Scanner scanner = new Scanner(System.in);
    String sender = "mitko.paleykov@gmail.com";

    String host = "smtp.gmail.com";
    Properties properties = new Properties();

    public void sendEmail(String emailText, String emailOfReceiver){
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            String password = System.getenv("2FA_PASS");
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailOfReceiver));
            message.setSubject("Best hours for sport");
            message.setText(emailText);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
