package com.example.vikas.homework2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentspace_1);
        if (frag == null) {
            Log.i(TAG, "fragment is  null");
            frag = new FragmentNew_A2_MT18086();
            fm.beginTransaction()
                    .add(R.id.fragmentspace_1, frag)
                    .commit();
        }
        Log.i(TAG, "after inflating");

    }
}
