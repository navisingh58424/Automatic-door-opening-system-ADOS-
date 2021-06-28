package com.example.ss;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.Message.RecipientType.CC;

class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;
    private String email, subject, message;
    String TAG="IN JAVAMAIL";
    public JavaMailAPI(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
        Log.d("inconstructirjavamail ","thread is "+Thread.currentThread());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("indoinbackgrdfunc","thread is "+Thread.currentThread());
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        Log.d(TAG,"curr thread inside "+Thread.currentThread());
        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));

            //     InternetAddress.parse("iit2019093@iiita.ac.in"),

            //mimeMessage.addRecipients( CC, String.valueOf(cc));
            mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));
            // mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            // Address[] cc = new Address[] {InternetAddress.parse("abc@abc.com"),
            //       InternetAddress.parse("abc@def.com"),
            //     InternetAddress.parse("ghi@abc.com")};
            //message.addRecipients(Message.RecipientType.CC, cc);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}