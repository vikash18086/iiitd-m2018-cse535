package com.example.vikas.homework2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver_1_A2_MT18086 extends BroadcastReceiver {
    private static final String DEBUG_TAG = "LogService";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Power-Connected-successfully", Toast.LENGTH_LONG).show();
        Log.i(DEBUG_TAG,"Power-Connected-successfully");
    }
}