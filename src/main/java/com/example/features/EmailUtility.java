package com.example.features;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class EmailUtility {

    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress,
                                 String subject, String message) throws AddressException, MessagingException {

        // Set SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // For SSL, enable this if port is 465
        if (port.equals("465")) {
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.starttls.enable", "false");
        }

        // Create an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        // Initialize session with properties and authenticator
        Session session = Session.getInstance(properties, auth);
        session.setDebug(true); // Enable debug logging

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(message);

            // Send the email
            Transport.send(msg);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Error while sending email: " + e.getMessage());
            throw e; // Re-throw for caller to handle
        }
    }
}
