package com.example.vikas.homework2;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class SongService_A2_MT18086 extends Service{
    MediaPlayer myPlayer;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onCreate(){
        Toast.makeText(this,"Service created", Toast.LENGTH_SHORT).show();
        myPlayer = MediaPlayer.create(this, FragmentNew_A2_MT18086.uriList[FragmentNew_A2_MT18086.index]);
        myPlayer.setLooping(false); // Set looping
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        myPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
        myPlayer.stop();
    }

}