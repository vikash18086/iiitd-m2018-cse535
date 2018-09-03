package com.example.vikas.homework2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver_3_A2_MT18086 extends BroadcastReceiver {
    private static final String DEBUG_TAG = "LogService";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Airplane_Mode_On", Toast.LENGTH_SHORT).show();
        Log.i(DEBUG_TAG,"Airplane_Mode_On");
    }
}
