package com.example.ss;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ss.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int l=0;
    int r=0;
    String []jobs=new String[10000];
    public static MainActivity instance;
    public int summ=0;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15;
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    EditText et0,et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

       instance=this;
        for(int i=0;i<10000;i++)
            jobs[i]="";
        et0=findViewById(R.id.editTextTextPersonName);
        et1=findViewById(R.id.editTextTextPersonName2);
        et2=findViewById(R.id.editTextTextPersonName3);
        et3=findViewById(R.id.editTextTextPersonName4);
        et4=findViewById(R.id.editTextTextPersonName5);
        et5=findViewById(R.id.editTextTextPersonName6);
        et6=findViewById(R.id.editTextTextPersonName8);
        et7=findViewById(R.id.editTextTextPersonName9);
        et8=findViewById(R.id.editTextTextPersonName10);
        et9=findViewById(R.id.editTextTextPersonName11);
        et10=findViewById(R.id.editTextTextPersonName12);
        et11=findViewById(R.id.editTextTextPersonName13);
        et12=findViewById(R.id.editTextTextPersonName14);
        et13=findViewById(R.id.editTextTextPersonName15);
        et14=findViewById(R.id.editTextTextPersonName16);

        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView4);
        tv3=findViewById(R.id.textView5);
        tv4=findViewById(R.id.textView6);
        tv5=findViewById(R.id.textView7);
        tv6=findViewById(R.id.textView8);
        tv7=findViewById(R.id.textView9);
        tv8=findViewById(R.id.textView10);
        tv9=findViewById(R.id.textView11);
        tv10=findViewById(R.id.textView12);
        tv11=findViewById(R.id.textView13);
        tv12=findViewById(R.id.textView14);
        tv13=findViewById(R.id.textView15);
        tv14=findViewById(R.id.textView16);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

//    public void loginperson(View v) {
//        String s1=tv9.getText().toString();
//        String s2=tv10.getText().toString();
//        Registered r=new Registered(MainActivity.getInstance());
//        r.open();
//        Cursor cursor=r.fetchData(s1);
//        String KEY_1="_id";
//        String KEY_2="person_name";
//        int iRowidd=cursor.getColumnIndex(KEY_1);
//        int iPass=cursor.getColumnIndex(KEY_2);
//        if(cursor.getCount()<=0)
//        {
//            Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else {
//            String chk1 = "";
//            String chk2 = "";
//            chk1 = cursor.getString(iRowidd);
//            chk2=cursor.getString(iPass);
//            if(chk2==s2)
//            {
//               // Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        //time slot confirmation
//
//
//        cursor.close();
//        r.close();
//
//        CompanyDb db=new CompanyDb(MainActivity.getInstance());
//        db.open();
//        Cursor cursor2=db.fetchData(s1);
//        String KEY_ROWID="_id";
//        String KEY_NAME="person_name";
//        String KEY_MAIL="person_mail";
//        String KEY_PHONE="person_phone";
//        String KEY_OUT="out_time";
//        String result="your details are \n ";
//        int iRowID = cursor2.getColumnIndex(KEY_ROWID);
//        int iName = cursor2.getColumnIndex(KEY_NAME);
//        int iCell = cursor2.getColumnIndex(KEY_MAIL);
//        int iPhone=cursor2.getColumnIndex(KEY_PHONE);
//        int iTime=cursor2.getColumnIndex(KEY_OUT);
//        if(cursor2.getCount()<=0)
//        {
//            Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            result = result + cursor2.getString(iRowID) + ": " + cursor2.getString(iName) + " " +
//                    cursor2.getString(iCell) +" "+cursor2.getString(iPhone)+" "+  cursor2.getString(iTime) +"\n";
//        }
//        String timing=cursor2.getString(iTime);
//        cursor2.close();
//        db.close();
//
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
//        String time=format.format(calendar.getTimeInMillis());
//        int g=calendar.get(Calendar.HOUR_OF_DAY);
//        int hhh=calendar.get(Calendar.HOUR);
//        int hh=calendar.get(Calendar.MINUTE);
//        int m=g*3600+hh*60;
//        int dayofweek=calendar.get(Calendar.DAY_OF_WEEK);
//        String weekday=new DateFormatSymbols().getShortWeekdays()[dayofweek];
//        int indd=0;
//        int cntr=0;
//        while(cntr<dayofweek)
//        {
//            if(timing.charAt(indd)=='-')
//            {
//                cntr++;
//            }
//            indd++;
//        }
//        int indd2=indd;
//        while(indd2<timing.length())
//        {
//            if(timing.charAt(indd2)==';')
//            {
//                break;
//            }
//            indd2++;
//        }
//
//
//        while(indd<indd2) {
//            int hr1 = 10 * (timing.charAt(indd) - '0') + timing.charAt(indd + 1) - '0';
//            int min1 = 10 * (timing.charAt(indd + 3) - '0') + timing.charAt(indd + 4) - '0';
//            int hr2 = 10 * (timing.charAt(indd + 7) - '0') + timing.charAt(indd + 8) - '0';
//            int min2 = 10 * (timing.charAt(indd + 10) - '0') + timing.charAt(indd + 11) - '0';
//            int time1 = 60 * hr1 + min1;
//            time1 = 60 * time1;
//            int time2 = 60 * hr2 + min2;
//            time2 = 60 * time2;
//            if (m >= time1 && m < time2) {
//                result = result + " and you are looged in";
//                tv1.setText(result);
//                String pass = Integer.toString(time2);
//                String f = "n" + "C" + s1 + " " + s2 + " " + pass;
//                int rr = MainActivity.getInstance().r;
//                jobs[rr] = f;
//                rr++;
//                rr = rr % 10000;
//                MainActivity.getInstance().r = rr;
//            } else {
//                Toast.makeText(this, "you are not allowed at this time slots", Toast.LENGTH_SHORT).show();
//                result = result + "  come in alloted time slots";
//                tv1.setText(result);
//            }
//            indd=indd+13;
//        }
//    }
//
//    public void exitperson(View v)
//    {
//        String s1=tv9.getText().toString();
//        String s2=tv10.getText().toString();
//        Registered rr=new Registered(MainActivity.getInstance());
//        rr.open();
//        Cursor cursor=rr.fetchData(s1);
//        String KEY_1="_id";
//        String KEY_2="person_name";
//        int iRowidd=cursor.getColumnIndex(KEY_1);
//        int iPass=cursor.getColumnIndex(KEY_2);
//        if(cursor.getCount()<=0)
//        {
//            Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show();
//            return;
//
//        }
//        else {
//            String chk1 = "";
//            String chk2 = "";
//            chk1 = cursor.getString(iRowidd);
//            chk2=cursor.getString(iPass);
//            if(chk2==s2) {
//                String f="n"+"D"+s1;
//                jobs[r]=f;
//                r++;
//                r=r%10000;
//                Toast.makeText(this, "YOU ARE EXITED", Toast.LENGTH_SHORT).show();
//
//            }
//            else
//            {
//                Toast.makeText(this, "ENTER VALID DETAILS", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        cursor.close();
//        rr.close();
//        return;
//    }
//    public void personregistration(View v) {
//        String s1=tv9.getText().toString();
//        String s2=tv10.getText().toString();
//        Registered r=new Registered(MainActivity.getInstance());
//        r.open();
//        Cursor cursor=r.fetchData(s1);
//        String KEY_1="_id";
//        String KEY_2="person_name";
//        int iRowidd=cursor.getColumnIndex(KEY_1);
//        int iPass=cursor.getColumnIndex(KEY_2);
//        if(cursor.getCount()<=0)
//        {
//            //Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show();
//            //return;
//        }
//        else {
//            String chk1 = "";
//            String chk2 = "";
//            chk1 = cursor.getString(iRowidd);
//            chk2=cursor.getString(iPass);
//            if(chk2==s2) {
//                Toast.makeText(this, "YOU ARE ALREADY REGISTERED", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        cursor.close();
//        r.close();
//        //now do a new registration
//        try {
//            Log.d("we are creating ","thread "+Thread.currentThread());
//            Registered r2=new Registered(MainActivity.getInstance());
//            r2.open();
//            r2.createEntry(s1,s2);
//            r2.close();
//            //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//
//        CompanyDb db=new CompanyDb(MainActivity.getInstance());
//        db.open();
//        Cursor cursor2=db.fetchData(s1);
//        String KEY_ROWID="_id";
//        String KEY_NAME="person_name";
//        String KEY_MAIL="person_mail";
//        String KEY_PHONE="person_phone";
//        String KEY_OUT="out_time";
//        String result="";
//        int iRowID = cursor2.getColumnIndex(KEY_ROWID);
//        int iName = cursor2.getColumnIndex(KEY_NAME);
//        int iCell = cursor2.getColumnIndex(KEY_MAIL);
//        int iPhone=cursor2.getColumnIndex(KEY_PHONE);
//        int iTime=cursor2.getColumnIndex(KEY_OUT);
//        if(cursor2.getCount()<=0)
//        {
//            Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            result = result + cursor2.getString(iRowID) + ": " + cursor2.getString(iName) + " " +
//                    cursor2.getString(iCell) +" "+cursor2.getString(iPhone)+" "+  cursor2.getString(iTime) +"\n";
//        }
//        cursor2.close();
//        db.close();
//
//        tv1.setText("you are registered and details are "+result+" CHECK mail");
//        String mail=cursor2.getString(iCell);
//
//        javax.mail.Session session;
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.port", "465");
//        Log.d(TAG,"curr thread inside "+Thread.currentThread());
//
//        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
//            }
//        });
//        MimeMessage mimeMessage = new MimeMessage(session);
//        try {
//            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));
//            mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mail));
//            String subject="yes it is the subject hpefully and just a check ";
//            String message="yes it is the message hopefully and just a check ";
//            mimeMessage.setSubject(subject);
//            mimeMessage.setText(message);
//            Transport.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void showcontacts(View v)
//    {
//        ContactsDb db=new ContactsDb(MainActivity.getInstance());
//        db.open();
//        String take=db.getdata2();
//        db.close();
//        tv1.setText(take);
//    }
//    public void showregister(View v)
//    {
//        Registered rr=new Registered(MainActivity.getInstance());
//        rr.open();
//        String take=rr.getdata();
//        rr.close();
//        tv1.setText(take);
//    }
//    public void showcompany(View v)
//    {
//        CompanyDb ccc=new CompanyDb(MainActivity.getInstance());
//        ccc.open();
//        String take=ccc.getdata2();
//        ccc.close();;
//        tv1.setText(take);
//    }
//    public void scheduleJob() {
//        JobScheduler scheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
//        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
//        JobInfo info = new JobInfo.Builder(123, componentName)
//                // .setRequiresCharging(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true)
//                .setPeriodic(15*60 * 1000)
//                .build();
//        // JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        // int resultCode = scheduler.schedule(info);
//        scheduler.schedule(info);
//       /* if (resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled");
//        } else {
//            Log.d(TAG, "Job scheduling failed");
//        }*/
//        Log.d("mnnnn","AT LAST IN FUNC SCHDULEJOB "+Thread.currentThread());
//    }
//    public void cancelJob() {
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.cancel(123);
//        Log.d("mnnnn", "Job cancelled");
//    }
   public static MainActivity getInstance(){
        return instance;
    }
//    String mgrpass="wedfffewww12";
//
//    public void addorchangedetails(View v){
//        String s0=et0.getText().toString();
//        String s1=et1.getText().toString();
//        String s2=et2.getText().toString();
//        String s3=et3.getText().toString();
//        String s4=et4.getText().toString();
//        String s5=et5.getText().toString();
//        String s6=et6.getText().toString();
//        String s7=et7.getText().toString();
//        String s8=et8.getText().toString();
//        String s9=et10.getText().toString();
//        String s10=et11.getText().toString();
//        String s11=et13.getText().toString();
//        String times="sun-"+s5+",mon-"+s6+",tue-"+s7+",wed-"+s8+",thu-"+s9+",fri-"+s10+",sat-"+s11;
//        if(s0==mgrpass)
//        {
//            CompanyDb db=new CompanyDb(MainActivity.getInstance());
//            db.open();
//            Cursor cursor2=db.fetchData(s1);
//            String KEY_ROWID="_id";
//            String KEY_NAME="person_name";
//            String KEY_MAIL="person_mail";
//            String KEY_PHONE="person_phone";
//            String KEY_OUT="out_time";
//            String result="";
//            int iRowID = cursor2.getColumnIndex(KEY_ROWID);
//            int iName = cursor2.getColumnIndex(KEY_NAME);
//            int iCell = cursor2.getColumnIndex(KEY_MAIL);
//            int iPhone=cursor2.getColumnIndex(KEY_PHONE);
//            int iTime=cursor2.getColumnIndex(KEY_OUT);
//            if(cursor2.getCount()<=0)
//            {
//                try {
//                    Log.d("we are creating ","thread "+Thread.currentThread());
//                    CompanyDb ddb = new CompanyDb(MainActivity.getInstance());
//                    ddb.open();
//                    ddb.createEntry(s1, s2, s3,s4,times);
//                    ddb.close();
//                    //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//                } catch (SQLException e) {
//                    Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            else
//            {
//                Log.d("we are destroying ","thread "+Thread.currentThread()+" ");
//                try{
//                    ContactsDb dbb=new ContactsDb(MainActivity.getInstance());
//                    dbb.open();
//                    dbb.deleteEntry(s1);
//                    dbb.close();
//                    //   Toast.makeText(MainActivity.getInstance(),"Sucessfully deleted||",Toast.LENGTH_SHORT).show();
//                }
//                catch (android.database.SQLException e)
//                {
//                    Toast.makeText(MainActivity.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    Log.d("we are creating ","thread "+Thread.currentThread());
//                    CompanyDb ddb = new CompanyDb(MainActivity.getInstance());
//                    ddb.open();
//                    ddb.createEntry(s1, s2, s3,s4,times);
//                    ddb.close();
//                    //   Toast.makeText(MainActivity.getInstance(), "Successfully saved", Toast.LENGTH_SHORT).show();
//                } catch (SQLException e) {
//                    Toast.makeText(MainActivity.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            cursor2.close();
//            db.close();
//        }
//        else
//        {
//            Toast.makeText(this,"entervalid manager pass ",Toast.LENGTH_SHORT).show();
//        }
//    }
}