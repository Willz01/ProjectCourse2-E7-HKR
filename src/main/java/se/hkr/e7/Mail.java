package se.hkr.e7;

import se.hkr.e7.model.Person;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Properties;

public class Mail {

    private static final Properties properties;
    private static Session session;

    static {
        properties = new Properties();
        try (
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/mail.properties");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)
        ) {
            properties.load(inputStreamReader);
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            properties.getProperty("mail.smtp.user"),
                            properties.getProperty("mail.smtp.password")
                    );
                }
            });
        } catch (IOException ignored) {
        }
    }

    private final Message message;

    public Mail() throws MessagingException, UnsupportedEncodingException {
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(
                properties.getProperty("mail.smtp.from"),
                properties.getProperty("mail.smtp.from.name")
        ));
    }

    public static void send(String subject, String body, Person... recipients) throws MessagingException, UnsupportedEncodingException {
        Mail mail = new Mail();
        mail.setTo(recipients);
        mail.setSubject(subject);
        mail.setBody(body);
        mail.send();
    }

    public static String generatePassword(int length) {
        final String pool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(pool.length());
            stringBuilder.append(pool.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    public void setTo(Person... recipients) throws MessagingException, UnsupportedEncodingException {
        message.setRecipients(Message.RecipientType.TO, null);
        for (Person recipient : recipients) {
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipient.getEmail(), recipient.getName()));
        }
    }

    public void setSubject(String subject) throws MessagingException {
        message.setSubject(subject);
    }

    public void setBody(String body) throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=UTF-8");
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(mimeBodyPart);
        message.setContent(mimeMultipart);
    }

    public void send() throws MessagingException {
        Transport.send(message);
    }
}
