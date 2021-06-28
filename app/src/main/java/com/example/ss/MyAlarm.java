package com.example.ss;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class MyAlarm extends BroadcastReceiver {

    //the method will be fired when the alarm is triggerred
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        //MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        //mediaPlayer.start();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //mediaPlayer.stop();
        JobScheduler scheduler = (JobScheduler) MainActivity.getInstance().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(MainActivity.getInstance(), ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                // .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
               // .setPeriodic(15*60 * 1000)
                .build();
        // JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        // int resultCode = scheduler.schedule(info);
        scheduler.schedule(info);
       /* if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }*/
        Log.d("mnnnn","AT LAST IN FUNC SCHDULEJOB "+Thread.currentThread());
        Log.d("MyAlarmBelal", "Alarm just fired"+ Thread.currentThread());
    }

}