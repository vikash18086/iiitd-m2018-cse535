package com.example.vikas.homework2;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import android.app.IntentService;

public class DownloadIntentService_A2_MT18086 extends IntentService{

    public DownloadIntentService_A2_MT18086(){ super("DownloadIntentService_A2_MT18086");}
    private static final String DEBUG_TAG = "Log Service";
    private int result = Activity.RESULT_CANCELED;

    @Override
    protected void onHandleIntent(Intent intent) {
        String urli=intent.getStringExtra("URL");
        String fileName = intent.getStringExtra("Filename");
        //File output = new File(Environment.getExternalStorageDirectory(),fileName);
        File output = new File(this.getFilesDir(),fileName);
        if(output.exists()){
            output.delete();
        }
        InputStream ios = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(urli);
            URLConnection conn = url.openConnection();
            ios = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(ios);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1) {
                Log.i(DEBUG_TAG,"The data is"+next);
                fos.write(next);
            }
            result=Activity.RESULT_OK;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(ios!=null){
                try{
                    ios.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try{
                    fos.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskinfo = activityManager.getRunningTasks(1);
        for(ActivityManager.RunningTaskInfo api: taskinfo){
            Log.i(DEBUG_TAG,"Top activity is"+api.topActivity.getPackageName());
        }
        publishResults(output.getAbsolutePath(),result);
    }
    private void publishResults(String outPath, int result){
        Intent intent = new Intent("com.example.vikas.homework2;");
        intent.putExtra("Filepath",outPath);
        intent.putExtra("Result",result);
        sendBroadcast(intent);
    }
}
