package com.example.ss;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static java.lang.StrictMath.min;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_two} factory method to
 * create an instance of this fragment.
 */
public class Frag_two extends Fragment {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15;
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13;
    EditText et0,et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15;
    String TAG="INSIDE FRAG_TWO";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_frag_two,container,false);
        et0=v.findViewById(R.id.editTextTextPersonName);
        et1=v.findViewById(R.id.editTextTextPersonName2);
        et2=v.findViewById(R.id.editTextTextPersonName3);
        et3=v.findViewById(R.id.editTextTextPersonName4);
        et4=v.findViewById(R.id.editTextTextPersonName5);
        et5=v.findViewById(R.id.editTextTextPersonName6);
        et6=v.findViewById(R.id.editTextTextPersonName8);
        et7=v.findViewById(R.id.editTextTextPersonName9);
        et8=v.findViewById(R.id.editTextTextPersonName10);
        et9=v.findViewById(R.id.editTextTextPersonName11);
        et10=v.findViewById(R.id.editTextTextPersonName12);
        et11=v.findViewById(R.id.editTextTextPersonName13);
        et12=v.findViewById(R.id.editTextTextPersonName14);
        et13=v.findViewById(R.id.editTextTextPersonName15);
        et14=v.findViewById(R.id.editTextTextPersonName16);
        et15=v.findViewById(R.id.editTextTextPersonName19);
        tv1=v.findViewById(R.id.textView);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        tv2=v.findViewById(R.id.textView4);
        tv3=v.findViewById(R.id.textView5);
        tv4=v.findViewById(R.id.textView6);
        tv5=v.findViewById(R.id.textView7);
        tv6=v.findViewById(R.id.textView8);
        tv7=v.findViewById(R.id.textView9);
        tv8=v.findViewById(R.id.textView10);
        tv9=v.findViewById(R.id.textView11);
        tv10=v.findViewById(R.id.textView12);
        tv11=v.findViewById(R.id.textView13);
        tv12=v.findViewById(R.id.textView14);
        tv13=v.findViewById(R.id.textView15);
        tv14=v.findViewById(R.id.textView16);

        bt1=v.findViewById(R.id.button);
        bt2=v.findViewById(R.id.button2);
        bt3=v.findViewById(R.id.button3);
        bt4=v.findViewById(R.id.button4);
        bt5=v.findViewById(R.id.button5);
        bt6=v.findViewById(R.id.button6);
        bt7=v.findViewById(R.id.button7);
        bt8=v.findViewById(R.id.button8);
        bt9=v.findViewById(R.id.button9);
        bt10=v.findViewById(R.id.button10);
        bt11=v.findViewById(R.id.button11);
        bt12=v.findViewById(R.id.button12);
        bt13=v.findViewById(R.id.button13);
        //register
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=et9.getText().toString();
                String s2=et12.getText().toString();
                et9.setText("");
                et12.setText("");
                Registered r=new Registered(MainActivity.getInstance());
                r.open();
                String r_get=r.fetchData(s1);

                    if(r_get.equals("")) {
                    }
                    else{
                            Toast.makeText(MainActivity.getInstance(), "YOU ARE ALREADY REGISTERED", Toast.LENGTH_SHORT).show();
                        tv1.setText("YOU ARE ALREADY REGISTERED");
                            return;
                        }

                r.close();

                CompanyDb db=new CompanyDb(MainActivity.getInstance());
                db.open();
                String get_cmp=db.fetchData(s1);
                String KEY_ROWID="_id";
                String KEY_NAME="person_name";
                String KEY_MAIL="person_mail";
                String KEY_PHONE="person_phone";
                String KEY_OUT="time_slots";
                String result="";
                if(get_cmp.equals(""))
                {
                    Toast.makeText(MainActivity.getInstance(),"you are not a member of company",Toast.LENGTH_LONG).show();
                    tv1.setText("you are not a member of company");
                    return;
                }
                //now do a new registration
                try {
                    Log.d("we are creating ","thread "+Thread.currentThread());
                    Registered r2=new Registered(MainActivity.getInstance());
                    r2.open();
                    r2.createEntry(s1,s2);
                    r2.close();
                    FlagDb r3=new FlagDb(MainActivity.getInstance());
                    r3.open();
                    r3.createEntry(s1,"");
                    r3.close();
                    //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                String gg="";
                for(int j=0;j<get_cmp.length();j++)
                {
                    if(get_cmp.charAt(j)==' '||get_cmp.charAt(j)==';')
                        gg=gg+"\n";
                    else
                        gg=gg+get_cmp.charAt(j);
                }
                tv1.setText("YOU ARE REGISTERED CHECK MAIL DETAILS-  "+gg+" CHECK mail");
                int ind1=0;
                int ind2=9999999;
                int ind3=0;
                int cnttt=0;
                for(int i=0;i<get_cmp.length();i++)
                {
                    if(get_cmp.charAt(i)==' ')
                    {
                        cnttt++;
                    }
                    if(cnttt==2)
                    {
                       // Toast.makeText(MainActivity.getInstance(),"ind2 "+i,Toast.LENGTH_LONG).show();
                        ind2=min(ind2,i);
                    }
                    if(cnttt==3)
                    {
                        ind3=i;
                       // Toast.makeText(MainActivity.getInstance(),"ind3 "+i,Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                ind2++;
                String mail="";
                for(int i=ind2;i<ind3;i++)
                {
                    mail=mail+get_cmp.charAt(i);
                }
                //Toast.makeText(MainActivity.getInstance(),mail,Toast.LENGTH_LONG).show();
                db.close();
                JavaMailAPI javaMailAPI = new JavaMailAPI(MainActivity.getInstance(), mail, "registration", "you are registered");
                javaMailAPI.execute();
            }
        });

        //login
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=et9.getText().toString();
                String s2=et12.getText().toString();
                et9.setText("");
                et12.setText("");
                Registered r=new Registered(MainActivity.getInstance());
                r.open();
                String r_get=r.fetchData(s1);
                String KEY_1="_id";
                String KEY_2="person_name";

                if(!(r_get.equals(s2)))
                {
                    Toast.makeText(getActivity(),"Enter valid details",Toast.LENGTH_SHORT).show();
                    tv1.setText("ENTER VALID DETAILS");
                    return;
                }
                //time slot confirmation
                r.close();

                CompanyDb db=new CompanyDb(MainActivity.getInstance());
                db.open();
                String result=db.fetchData(s1);
                String gg="";

                for(int j=0;j<result.length();j++)
                {
                    if(result.charAt(j)==' '||result.charAt(j)==';')
                        gg=gg+"\n";
                    else
                        gg=gg+result.charAt(j);
                }




                String timing="";
                if(result.length()==0)
                {
                    Toast.makeText(getActivity(),"  Not a member of company anymore",Toast.LENGTH_SHORT).show();
                    tv1.setText("NOT A MEMBER OF COMPANY ANYMORE");
                    return;
                }
                db.close();
                int pt1=99999;
                int pt2=0;
                int cntt=0;
                for(int i=0;i<result.length();i++)
                {
                    if(result.charAt(i)==' ')
                    {
                        cntt++;
                    }
                    if(cntt==4)
                    {
                        pt1=min(pt1,i);
                    }
                    if(cntt==5)
                    {
                        pt2=i;
                    }
                }
                pt1++;
                for(int i=pt1;i<pt2;i++)
                {
                    timing=timing+result.charAt(i);
                }
                Log.d("timing is ",timing);
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("HH:mm");
                String time=format.format(calendar.getTimeInMillis());
                int g=calendar.get(Calendar.HOUR_OF_DAY);
                int hhh=calendar.get(Calendar.HOUR);
                int hh=calendar.get(Calendar.MINUTE);
                int m=g*3600+hh*60;
                int dayofweek=calendar.get(Calendar.DAY_OF_WEEK);
                String weekday=new DateFormatSymbols().getShortWeekdays()[dayofweek];
                int indd=0;
                int cntr=0;
                //COMMOENT
                while(cntr<dayofweek)
                {
                    if(timing.charAt(indd)=='-')
                    {
                        cntr++;
                    }
                    indd++;
                }
                int indd2=indd;
                while(indd2<timing.length())
                {
                    if(timing.charAt(indd2)==';')
                    {
                        break;
                    }
                    indd2++;
                }
                CompanyDb dbbb=new CompanyDb(MainActivity.getInstance());
                dbbb.open();
                String person_mail=dbbb.giveMail(s1);
                Log.d("permail","ml h "+person_mail);
                dbbb.close();
                while(indd<indd2) {
                    int hr1 = 10 * (timing.charAt(indd) - '0') + timing.charAt(indd + 1) - '0';
                    int min1 = 10 * (timing.charAt(indd + 3) - '0') + timing.charAt(indd + 4) - '0';
                    int hr2 = 10 * (timing.charAt(indd + 7) - '0') + timing.charAt(indd + 8) - '0';
                    int min2 = 10 * (timing.charAt(indd + 10) - '0') + timing.charAt(indd + 11) - '0';
                    int time1 = 60 * hr1 + min1;
                    time1 = 60 * time1;
                    int time2 = 60 * hr2 + min2;
                    time2 = 60 * time2;
                    Log.d("times ","tt "+time1+" "+time2+" "+m);
                    if (m >= time1 && m < time2) {
                        result = result + " and you are looged in";
                        String ghh="YOU ARE LOGGED IN ";
                        ghh=ghh+gg;
                        tv1.setText(ghh);
                        String pass = Integer.toString(time2);
                        String f = "n" + "C" + s1 + " " + s2 +" "+ person_mail+" " + pass;
                        int rr = MainActivity.getInstance().r;
                        MainActivity.getInstance().jobs[rr] = f;
                        rr++;
                        rr = rr % 10000;
                        MainActivity.getInstance().r = rr;
                        //critical section
                        try {
                            Log.d("we are creating ","thread "+Thread.currentThread()+" "+" "+s1);
                            ContactsDb ddb = new ContactsDb(MainActivity.getInstance());
                            ddb.open();
                            ddb.createEntry(s1, s2, person_mail,pass);
                            ddb.close();
                            //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
                        } catch (SQLException e) {
                            Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //critical section
                        FlagDb ddd=new FlagDb(MainActivity.getInstance());
                        ddd.open();
                        ddd.updateEntry(s1,"k");
                        ddd.close();
                        return;
                    }
                    indd=indd+13;
                }
                Toast.makeText(MainActivity.getInstance(), "not allowed at this time", Toast.LENGTH_LONG).show();
                String ree =  "come in alloted time slots";
                tv1.setText(ree);
            }
        });
        //exit
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=et9.getText().toString();
                String s2=et12.getText().toString();
                et9.setText("");
                et12.setText("");
                ContactsDb rabi=new ContactsDb(MainActivity.getInstance());
                rabi.open();
                String r_get=rabi.fetchData(s1);
                if(r_get.equals("notfound\n"))
                {
                    tv1.setText("YOU HAVE NOT ENTERED THE BUILDING");
                    Toast.makeText(MainActivity.getInstance(), "YOU HAVE NOT ENTERED THE BUILDING", Toast.LENGTH_SHORT).show();
                    return;
                }
                Registered rr=new Registered(MainActivity.getInstance());
                rr.open();
                Log.d("inexit","thread "+Thread.currentThread());
                String get_rr=rr.fetchData(s1);
                Log.d("inexi2t","thread "+Thread.currentThread());
                String KEY_1="_id";
                Log.d("inexi3t","thread "+Thread.currentThread());
                String KEY_2="person_name";
                Log.d("inexi4t","thread "+Thread.currentThread());
                if(get_rr.equals(s2)&&get_rr.length()!=0) {
                    Log.d("inexi5t","thread "+Thread.currentThread());
                        String f="n"+"D"+s1+" ";
                        int ggg=MainActivity.getInstance().r;
                        MainActivity.getInstance().jobs[ggg]=f;
                        ggg++;
                        ggg=ggg%10000;
                        MainActivity.getInstance().r=ggg;
                        Toast.makeText(MainActivity.getInstance(), "YOU ARE EXITED", Toast.LENGTH_SHORT).show();
                    tv1.setText("YOU ARE EXITED");
                    FlagDb ddd=new FlagDb(MainActivity.getInstance());
                    ddd.open();
                    ddd.updateEntry(s1,"k");
                    ddd.close();
                    //critical section
                    try{
                        ContactsDb dbb=new ContactsDb(MainActivity.getInstance());
                        dbb.open();
                        dbb.deleteEntry(s1);
                        dbb.close();
                        //   Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
                    }
                    catch (android.database.SQLException e)
                    {
                        Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    //critical section
                    }
                    else {
                        Toast.makeText(MainActivity.getInstance(), "ENTER VALID DETAILS", Toast.LENGTH_SHORT).show();
                    tv1.setText("ENTER VALID DETAILS");
                        return;
                    }
                rr.close();
            }
        });
        //schedule
        bt5.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                JobScheduler scheduler = (JobScheduler) MainActivity.getInstance().getSystemService(JOB_SCHEDULER_SERVICE);
//                ComponentName componentName = new ComponentName(MainActivity.getInstance(), ExampleJobService.class);
//                JobInfo info = new JobInfo.Builder(123, componentName)
//                        // .setRequiresCharging(true)
//                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                        .setPersisted(true)
//                        .setPeriodic(15*60 * 1000)
//                        .build();
//                // JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//                // int resultCode = scheduler.schedule(info);
//                scheduler.schedule(info);
       /* if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }*/
                String s2=et15.getText().toString();
                et15.setText("");
                if(s2.equals("we"))
                {
                  tv1.setText("SCHEDULER STARTED");
                }
                else
                {
                    Toast.makeText(MainActivity.getInstance(), "ENTER VALID MANAGER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                //creating a new intent specifying the broadcast receiver
                Intent i = new Intent(getActivity(), MyAlarm.class);

                //creating a pending intent using the intent
                PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0, i, 0);
                Log.d("Myyyyyyyy", "Alarm iiiii");
                //setting the repeating alarm that will be fired every day
             //   am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pi);
                am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),2*60*1000,pi);
                Log.d("mnnnn","AT LAST IN FUNC SCHDULEJOB "+Thread.currentThread());
            }
        });
        //cancel
        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String s1=et15.getText().toString();
                et15.setText("");
                if(s1.equals("we"))
                {
                     tv1.setText("SCHEDULER STOPPED");
                }
                else
                {
                    Toast.makeText(MainActivity.getInstance(), "ENTER VALID MANAGER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(123);
                Log.d("mnnnn", "Job cancelled");
            }
        });
        //show register
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registered rr=new Registered(MainActivity.getInstance());
                rr.open();
                String take=rr.getdata();
                rr.close();
                tv1.setText(take);
            }
        });
        //show contacts
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactsDb db=new ContactsDb(MainActivity.getInstance());
                db.open();
                String take=db.getdata2();
                db.close();
                tv1.setText(take);
            }
        });
        //show comp
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompanyDb ccc=new CompanyDb(MainActivity.getInstance());
                ccc.open();
                String take=ccc.getdata2();
                ccc.close();;
                tv1.setText(take);
            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String s1=et9.getText().toString();
                try{
                Registered dbb=new Registered(MainActivity.getInstance());
                dbb.open();
                dbb.deleteEntry(s1);
                dbb.close();
                    FlagDb r3=new FlagDb(MainActivity.getInstance());
                    r3.open();
                    r3.deleteEntry(s1);
                    r3.close();
                Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
            } catch (android.database.SQLException e)
            {
                Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            }
        });
        //password change
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et15.getText().toString();
                String s2 = et12.getText().toString();
                String s3 = et9.getText().toString();
                et9.setText("");
                et12.setText("");
                et15.setText("");
                if (s1.equals("we"))
                {
                    try{
                        Registered dbb=new Registered(MainActivity.getInstance());
                        dbb.open();
                        dbb.deleteEntry(s3);
                        dbb.close();
                        //FlagDb r3=new FlagDb(MainActivity.getInstance());
                        //r3.open();
                        //r3.deleteEntry(s2);
                        //r3.close();
                       // Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
                    } catch (android.database.SQLException e)
                    {
                        Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    try {
                        Log.d("we are creating ","thread "+Thread.currentThread());
                        Registered r2=new Registered(MainActivity.getInstance());
                        r2.open();
                        r2.createEntry(s3,s2);
                        r2.close();
                        //FlagDb r3=new FlagDb(MainActivity.getInstance());
                        //r3.open();
                        //r3.createEntry(s1,"");
                        //r3.close();
                        //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.getInstance(),"Sucessfully changed password",Toast.LENGTH_SHORT).show();
                    tv1.setText("SUCCESSFULLY CHANGED PASSWORD");

                }
                else
                {
                    Toast.makeText(MainActivity.getInstance(),"Incorrect manager password ",Toast.LENGTH_SHORT).show();
                    tv1.setText("INCORRECT MANAGER PASSWORD");
                }

            }
        });
        return v;
    }
}