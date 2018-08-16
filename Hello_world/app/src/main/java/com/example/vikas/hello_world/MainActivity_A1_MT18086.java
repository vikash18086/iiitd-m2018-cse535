package com.example.vikas.hello_world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity_A1_MT18086 extends AppCompatActivity {

    String msg1="\0",msg2="\0",msg3="\0",msg4="\0",msg5="\0",msg6="\0",msg7="\0";

    private static String state="";
    private static final String tag ="data";
    private static final String MSG ="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Activity-1 Created",Toast.LENGTH_SHORT).show();
        Log.i(tag,"Activity-1 is created");
        state = "Activity-1 On create ";

//        if(savedInstanceState != null){
//            Log.d("STATE",savedInstanceState.toString());
//        }
//        Log.d("CREATION" , "onCreate() is being executed!");
//        setContentView(R.layout.activity_main);
        //log created*****************************************************************

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"State of main activity change from "+state+" to On-start",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-1 On-start");
        state = "Activity-1 On-start";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"State of main activity change from "+state+" to on-Resume",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-1 on-Resume");
        state = "Activity-1 on-Resume";
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"State of main activity change from "+state+" to on-Pause",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-1 on-Pause");
        state = "Activity-1 on-Pause";
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"State of main activity change from "+state+" to on-Stop",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-1 on-Stop");
        state = "Activity-1 on-Stop";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"State of main activity change from "+state+" to on-Destroy",Toast.LENGTH_SHORT).show();
        Log.i(tag,"State change from "+state+" to Activity-1 on-Destroy");
        state = "Activity-1 on-Destroy";
    }

    public void svData(View v) {
        if ((((EditText) findViewById(R.id.name)).getText().toString().length() < 1)) {
            Toast.makeText(this, "Name can-not be Empty", Toast.LENGTH_SHORT).show();
        } else if ((((EditText) findViewById(R.id.rollnumber)).getText().toString().length() < 1)) {
            Toast.makeText(this, "Roll-no can-not be Empty", Toast.LENGTH_SHORT).show();
        } else if ((((EditText) findViewById(R.id.branch)).getText().toString().length() < 1)) {
            Toast.makeText(this, "Branch can-not be Empty", Toast.LENGTH_SHORT).show();
        }
        else if ((((EditText) findViewById(R.id.course1)).getText().toString().length() < 1)
                && (((EditText) findViewById(R.id.course2)).getText().toString().length() < 1)
                && (((EditText) findViewById(R.id.course3)).getText().toString().length() < 1)
                && (((EditText) findViewById(R.id.course4)).getText().toString().length() < 1))
        {
            Toast.makeText(this, "Atleast one course have to be selected", Toast.LENGTH_SHORT).show();
        }
        else {
            msg1 = ((EditText) findViewById(R.id.name)).getText().toString();
            msg2 = ((EditText) findViewById(R.id.rollnumber)).getText().toString();
            msg3 = ((EditText) findViewById(R.id.branch)).getText().toString();
            msg4 = ((EditText) findViewById(R.id.course1)).getText().toString();
            msg5 = ((EditText) findViewById(R.id.course2)).getText().toString();
            msg6 = ((EditText) findViewById(R.id.course3)).getText().toString();
            msg7 = ((EditText) findViewById(R.id.course4)).getText().toString();
            String[] records = {msg1, msg2, msg3, msg4, msg5, msg6, msg7};
            Intent intent = new Intent(getApplicationContext(), Student_Record_A1_MT18086.class);
            intent.putExtra(MSG, records);
            startActivity(intent);
        }
    }

    public void clrData(View v1)
    {
        ((EditText)findViewById(R.id.name)).getText().clear();
        ((EditText)findViewById(R.id.rollnumber)).getText().clear();
        ((EditText)findViewById(R.id.branch)).getText().clear();
        ((EditText)findViewById(R.id.course1)).getText().clear();
        ((EditText)findViewById(R.id.course2)).getText().clear();
        ((EditText)findViewById(R.id.course3)).getText().clear();
        ((EditText)findViewById(R.id.course4)).getText().clear();
    }
}