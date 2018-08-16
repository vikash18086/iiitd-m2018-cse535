package com.example.vikas.hello_world;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Record_A1_MT18086 extends AppCompatActivity {

    private static String state="";
    private static final String tag ="data";

    private static final String MSG ="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);
        Intent intent = getIntent();
        String []message = intent.getStringArrayExtra(MSG);

        Toast.makeText(this,"Activity-2 Created",Toast.LENGTH_SHORT).show();
        Log.i(tag,"Activity-2 is created");
        state = "Activity-2 On create ";


//      TextView textview0 = (TextView) findViewById(R.id.nameText);

        TextView textview0 = new TextView(this);
        textview0.setTextSize(20);
        textview0.setText("Name - "+message[0]+"\n"+"Roll-Number - "+message[1] + "\n"+"Branch - "+message[2] + "\n"
        +"Course.1 - "+message[3] + "\n" + "Course.2 - "+message[4] + "\n" + "Course.3 - "+message[5] + "\n"
        +"Course.4 - "+message[6] + "\n");
        setContentView(textview0);


//        TextView textView6 = new TextView(this);
//        textView6.setTextSize(30);
//        textView6.setText("Course.3 - "+message[5] + "\n");
//        setContentView(textView6);
//
//        TextView textView7 = new TextView(this);
//        textView7.setTextSize(30);
//        textView7.setText("Course.4 - "+message[6] + "\n");
//        setContentView(textView7);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"State of Activity-2 change from "+state+" to On-start",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-2 On-start");
        state = "Activity-2 On-start";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"State of Activity-2 change from "+state+" to on-Resume",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-2 on-Resume");
        state = "Activity-2 on-Resume";
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"State of Activity-2 change from "+state+" to on-Pause",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-2 on-Pause");
        state = "Activity-2 on-Pause";
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"State of Activity-2 change from "+state+" to on-Stop",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-2 on-Stop");
        state = "Activity-2 on-Stop";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"State of Activity-2 change from "+state+" to on-Destroy",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-2 on-Destroy");
        state = "Activity-2 on-Destroy";
    }
}