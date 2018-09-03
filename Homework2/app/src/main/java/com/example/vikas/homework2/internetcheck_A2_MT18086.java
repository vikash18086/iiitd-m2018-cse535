package com.example.vikas.homework2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.widget.Toast;

public class internetcheck_A2_MT18086 extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Internet_available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No_Internet", Toast.LENGTH_SHORT).show();
        }
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
