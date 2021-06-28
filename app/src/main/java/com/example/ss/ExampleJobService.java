package com.example.ss;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.database.SQLException;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG, "Job started "+Thread.currentThread());
        doBackgroundWork(params);
        return true;
    }
    public void doBackgroundWork(final JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int temp_l=1;
                int temp_r=2;
              //  temp_l=MainActivity.getInstance().l;
              //  temp_r=MainActivity.getInstance().r;
                Log.d("temp l and r ","thread "+Thread.currentThread()+" "+temp_l+" "+temp_r);

//                if(temp_r>temp_l) {
//                    MainActivity.getInstance().l=temp_r;
//                    for (int j = temp_l; j < temp_r; j++) {
//                        Log.d("insidedatabudateloop ","thread is "+Thread.currentThread());
//                        String get = MainActivity.getInstance().jobs[j];
//                        char c1=get.charAt(0);
//                        if(c1=='n')
//                        {
//                            String ss1,ss2,ss3,ss4;
//                            int h=2;
//                            ss1="";
//                            while(get.charAt(h)!=' ')
//                            {
//                                ss1=ss1+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss2="";
//                            while(h<get.length()&&get.charAt(h)!=' ')
//                            {
//                                ss2=ss2+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss3="";
//                            while(h<get.length()&&get.charAt(h)!=' ')
//                            {
//                                ss3=ss3+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss4="";
//                            while (h<get.length())
//                            {
//                                ss4=ss4+get.charAt(h);
//                                h++;
//                            }
//                            char c2=get.charAt(1);
//                            if(c2=='C') {
//                                try {
//                                    Log.d("we are creating ","thread "+Thread.currentThread()+" "+j+" "+ss1);
//                                    ContactsDb ddb = new ContactsDb(MainActivity.getInstance());
//                                    ddb.open();
//                                    ddb.createEntry(ss1, ss2, ss3,ss4);
//                                    ddb.close();
//                                    //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//                                } catch (SQLException e) {
//                                    Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else if(c2=='D'){
//                                Log.d("we are destroying ","thread "+Thread.currentThread()+" "+j+" "+ss1);
//                                try{
//                                    ContactsDb dbb=new ContactsDb(MainActivity.getInstance());
//                                    dbb.open();
//                                    dbb.deleteEntry(ss1);
//                                    dbb.close();
//                                    //   Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
//                                }
//                                catch (android.database.SQLException e)
//                                {
//                                    Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                        MainActivity.getInstance().jobs[j]="dC";
//                    }
//                }
                //mmmmm
                String []coll_for_mail=new String[1000];
                for(int i=0;i<1000;i++)
                {
                    coll_for_mail[i]="";
                }

                int indd=0;
                //data will be readed and coll_for_mail will be updated
                Log.d(TAG,"THREAD IS "+Thread.currentThread());
                ContactsDb db=new ContactsDb(MainActivity.getInstance());
                db.open();
                String take=db.getdata();
                db.close();
               /* int g=0;
                int bb=0;
                while(g!=take.length())
                {
                    if(take.charAt(g)=='\n')
                    {
                        bb++;
                        g++;
                        continue;
                    }
                    coll_for_mail[bb]=coll_for_mail[bb]+take.charAt(g);
                    g++;
                }*/
                String message_him="";
                Log.d("mailing sssszzeee","Thread "+Thread.currentThread()+" "+take.length());
                for(int j=0;j<take.length();j++)
                {
                    Log.d("mailing loop","Thread "+Thread.currentThread()+" "+j);
                    if(take.charAt(j)=='\n') {
                        Session session;
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
                            Log.d("yesinbakd","mee "+message_him);
                            mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(message_him));
                            String subject="TIME OUT ";
                            String message="ALLOTED TIME SLOT EXPIREED . PLZGET OUT OF BUILDING+ ";
                            mimeMessage.setSubject(subject);
                            mimeMessage.setText(message);
                            Transport.send(mimeMessage);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        message_him="";
                    }
                    message_him=message_him+take.charAt(j);
                }
                //update call_for_mail and mail each indv
                /**/
                //update the database
//                int temp_l;
//                int temp_r;
//                temp_l=MainActivity.getInstance().l;
//                temp_r=MainActivity.getInstance().r;
//                Log.d("temp l and r ","thread "+Thread.currentThread()+" "+temp_l+" "+temp_r);
//
//                if(temp_r>temp_l) {
//                    MainActivity.getInstance().l=temp_r;
//                    for (int j = temp_l; j < temp_r; j++) {
//                        Log.d("insidedatabudateloop ","thread is "+Thread.currentThread());
//                        String get = MainActivity.getInstance().jobs[j];
//                        char c1=get.charAt(0);
//                        if(c1=='n')
//                        {
//                            String ss1,ss2,ss3,ss4;
//                            int h=2;
//                            ss1="";
//                            while(get.charAt(h)!=' ')
//                            {
//                                ss1=ss1+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss2="";
//                            while(h<get.length()&&get.charAt(h)!=' ')
//                            {
//                                ss2=ss2+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss3="";
//                            while(h<get.length()&&get.charAt(h)!=' ')
//                            {
//                                ss3=ss3+get.charAt(h);
//                                h++;
//                            }
//                            h++;
//                            ss4="";
//                            while (h<get.length())
//                            {
//                                ss4=ss4+get.charAt(h);
//                                h++;
//                            }
//                            char c2=get.charAt(1);
//                            if(c2=='C') {
//                                try {
//                                    Log.d("we are creating ","thread "+Thread.currentThread()+" "+j+" "+ss1);
//                                    ContactsDb ddb = new ContactsDb(MainActivity.getInstance());
//                                    ddb.open();
//                                    ddb.createEntry(ss1, ss2, ss3,ss4);
//                                    ddb.close();
//                                    //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//                                } catch (SQLException e) {
//                                    Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else if(c2=='D'){
//                                Log.d("we are destroying ","thread "+Thread.currentThread()+" "+j+" "+ss1);
//                                try{
//                                    ContactsDb dbb=new ContactsDb(MainActivity.getInstance());
//                                    dbb.open();
//                                    dbb.deleteEntry(ss1);
//                                    dbb.close();
//                                    //   Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
//                                }
//                                catch (android.database.SQLException e)
//                                {
//                                    Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                        MainActivity.getInstance().jobs[j]="dC";
//                    }
//                }
                Log.d(TAG, "Job finished "+Thread.currentThread());
                jobFinished(params, false);
            }
        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion "+Thread.currentThread());
        jobCancelled = true;
        return true;
    }
}
