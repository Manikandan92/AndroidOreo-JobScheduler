package com.mallowtech.android_o_jobscheduler.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by manikandan on 04/12/17.
 */

public class AndroidOJobService extends JobService {
    private static final String TAG = "PAJobService";

    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        Toast.makeText(getApplicationContext(), "Job Started", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        return false;
    }
}