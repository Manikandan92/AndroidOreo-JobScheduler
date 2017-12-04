package com.mallowtech.android_o_jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mallowtech.android_o_jobscheduler.service.AndroidOJobService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "JOB";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleJob(MainActivity.this);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: " + true);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, AndroidOJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(Constant.JOB_ID, serviceComponent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(Constant.REFRESH_INTERVAL);
        } else {
            builder.setPeriodic(Constant.REFRESH_INTERVAL_1); // For interval millis --> 1min flexmillis ---> 10's
        }
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        int ret = jobScheduler.schedule(builder.build());
        if (ret == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled successfully");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }
}