package com.example.detyra_2fa;

import android.os.StrictMode;
import android.util.Log;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final String senderEmail = "gashilendiona@gmail.com";
    private static final String senderPassword = "eznfbbtqypiulrut";

    public static String createOTP() {
        return "1234";
    }

    public static void sendOTP(String recipientEmail, String otp, OTPCallback callback) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            Transport.send(message);
            Log.d("EmailSender", "OTP sent successfully to " + recipientEmail);
            callback.onResult(true);
        } catch (Exception e) {
            Log.e("EmailSender", "Error sending email: " + e.getMessage());
            callback.onResult(false);
        }
    }

    public interface OTPCallback {
        void onResult(boolean success);
    }
}
