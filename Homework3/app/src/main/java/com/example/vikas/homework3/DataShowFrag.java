package com.example.vikas.homework3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import static android.support.v4.content.ContextCompat.getSystemService;


public class DataShowFrag extends Fragment {


    private OnFragmentInteractionListener mListener;
    static List<Question> answerList = null;
    RadioButton r1,r2;
    static String t,t1="";
    int flag=0;
    Button btn_save ,btn_clear, btn_submit;
    RadioGroup rg;
    TextView txt_story ;
    public DataBase db;

    public DataShowFrag() {
        // Required empty public constructor
    }


    public static DataShowFrag newInstance(String param1, String param2) {
        DataShowFrag fragment = new DataShowFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        db= new DataBase(getActivity());
        View root_view =  inflater.inflate(R.layout.fragment_data_show, container, false);
        r1 =  root_view.findViewById(R.id.r1);
        r2 =  root_view.findViewById(R.id.r2);
        rg =  root_view.findViewById(R.id.rg);
        if(RecyclerViewFrag.questionList.get(RecyclerViewFrag.pos).getYouranswer().equalsIgnoreCase("true"))
        {
            rg.check(R.id.r1);
        }
        else if(RecyclerViewFrag.questionList.get(RecyclerViewFrag.pos).getYouranswer().equalsIgnoreCase("false"))
        {
            rg.check(R.id.r2);
        }


        Question question = new Question();

      //  answerList = new ArrayList<Question>();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.r1:
                        //int position = RecyclerViewFrag.pos;
                        //Question question = RecyclerViewFrag.questionList.get(position);
                        t = "True";
                        flag=1;
                        //String s1 = question.getId().toString();
                        //String s2 = question.getQuestion().toString();
                        break;

                    case R.id.r2:
                        //int position1 = RecyclerViewFrag.pos;
                        //Question question1 = RecyclerViewFrag.questionList.get(position1);
                        t = "False";
                        flag=1;
                        //String s11 = question1.getId().toString();
                        //String s21 = question1.getQuestion().toString();
                        break;
                }

            }
        });

        btn_clear = root_view.findViewById(R.id.clear_button);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                rg.clearCheck();
                RecyclerViewFrag.questionList.get(RecyclerViewFrag.pos).setYouranswer("");
                flag = 0;
            }
        });
        int position3 = RecyclerViewFrag.pos;
        btn_save =  root_view.findViewById(R.id.save_button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int position1 = RecyclerViewFrag.pos;
                if(flag==1) {
                    RecyclerViewFrag.questionList.get(position1).setYouranswer(t);
                }
                else
                {
                    RecyclerViewFrag.questionList.get(position1).setYouranswer(t1);
                }
                flag=0;
                    //question.setYouranswer(t);
                Log.i("LOG", RecyclerViewFrag.questionList.get(position1).getYouranswer());
                db.updateDatabase(RecyclerViewFrag.questionList.get(position1));

            }
        });

        btn_submit =  root_view.findViewById(R.id.submit_button);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                File exportDir = getActivity().getFilesDir();
                if (!exportDir.exists())
                {
                    exportDir.mkdirs();
                }
                File file = new File(exportDir, "VikasCSV.csv");
                try
                {
                    file.createNewFile();
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    SQLiteDatabase dbhelper = db.getReadableDatabase();
                    Cursor curCSV = dbhelper.rawQuery("SELECT * FROM " + Question.TABLE_NAME,null);
                    csvWrite.writeNext(curCSV.getColumnNames());
                    while(curCSV.moveToNext())
                    {
                        String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)};
                        csvWrite.writeNext(arrStr);
                    }
                    csvWrite.close();
                    curCSV.close();
                    Log.i("DBLOG","Export success to "+ file.getAbsoluteFile().toString());
                }
                catch(Exception sqlEx)
                {
                    Log.e("Exported Msg", sqlEx.getMessage(), sqlEx);
                }



                ConnectivityManager con_manager = (ConnectivityManager)getContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con_manager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getContext(), "Internet_available", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Submit_Successfull", Toast.LENGTH_SHORT).show();
                    new Server(getActivity()).execute(file.getAbsolutePath());
                } else {
                    Toast.makeText(getContext(), "No_Internet", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

        txt_story =  root_view.findViewById(R.id.qtextview);
        txt_story.setText(RecyclerViewFrag.s);
        return root_view;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}