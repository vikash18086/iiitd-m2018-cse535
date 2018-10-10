package com.example.vikas.homework3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;


public class RecyclerViewFrag extends Fragment {
    static List<Question> questionList = null;
    static int pos;
    private RecyclerView recyclerView;
    private QuestionAdapter qAdapter;
    DataBase db;
    static String s,s1;
    Button submit_button;
    private OnFragmentInteractionListener mListener;

    public RecyclerViewFrag() {
        // Required empty public constructor
    }

    public static RecyclerViewFrag newInstance(String param1, String param2) {
        RecyclerViewFrag fragment = new RecyclerViewFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void prepareQuestionData()
    {
        if(questionList == null) {

            questionList = new ArrayList<Question>();

            Question question1 = new Question("1", "Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.", "True","");

            questionList.add(question1);

            Question question2 = new Question("2", "Freeware is software that is available for use at no monetary cost.", "True","");
            questionList.add(question2);

            Question question3 = new Question("3", "IPv6 Internet Protocol address is represented as eight groups of four Octal digits.", "False","");
            questionList.add(question3);

            Question question4 = new Question("4", "The hexadecimal number system contains digits from 1 - 15.", "Flase","");
            questionList.add(question4);

            Question question5 = new Question("5", "Octal number system contains digits from 0 - 7.", "True","");
            questionList.add(question5);

            Question question6 = new Question("6", "MS Word is a hardware.", "False","");
            questionList.add(question6);

            Question question7 = new Question("7", "CPU controls only input data of computer.", "False","");
            questionList.add(question7);

            Question question8 = new Question("8", "CPU stands for Central Performance Unit.", "False","");
            questionList.add(question8);

            Question question9 = new Question("9", "The Language that the computer can understand is called Machine Language.", "True","");
            questionList.add(question9);

            Question question10 = new Question("10", "Magnetic Tape used random access method.", "False","");
            questionList.add(question10);

            Question question11 = new Question("11", "Twitter is an online social networking and blogging service.", "False","");
            questionList.add(question11);

            Question question12 = new Question("12", "Worms and trojan horses are easily detected and eliminated by antivirus software.", "True","");
            questionList.add(question12);

            Question question13 = new Question("13", "Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.", "True","");
            questionList.add(question13);

            Question question14 = new Question("14", "GNU / Linux is a open source operating system.", "True","");
            questionList.add(question14);

            Question question15 = new Question("15", " You can store Web-based e-mail messages in online folders.", "True","");
            questionList.add(question15);

            Question question16 = new Question("16", "You can delete e-mails from a Web-based e-mail account.", "True","");
            questionList.add(question16);

            Question question17 = new Question("17", "Web-based e-mail accounts do not required passwords.", "False","");
            questionList.add(question17);

            Question question18 = new Question("18", "You can sign up for Web-based e-mail without accepting the Web site's terms and conditions.", "False","");
            questionList.add(question18);

            Question question19 = new Question("19", "Your e-mail address must be unique.", "True","");
            questionList.add(question19);

            Question question20 = new Question("20", "You cannot send a file from a Web-based e-mail account.", "False","");
            questionList.add(question20);

            Question question21 = new Question("21", "You can only store messages in a new folder if they are received after you creat the folder. ", "False","");
            questionList.add(question21);

            Question question22 = new Question("22", "New folders must all be at the same level.", "False","");
            questionList.add(question22);

            Question question23 = new Question("23", "There is only one way to create a new folder.", "False","");
            questionList.add(question23);

            Question question24 = new Question("24", "Your password should be something others will be able to figured out, such as your birthday or wedding anniversary.", "False","");
            questionList.add(question24);

            Question question25 = new Question("25", "Pressing the Delete key and clicking the Delete button produce the same result.", "True","");
            questionList.add(question25);

            Question question26 = new Question("26", "The Delete key is for deleting text, it will not delete messages from your Inbox.", "False","");
            questionList.add(question26);

            Question question27 = new Question("27", "There is only one way to delete a message.", "False","");
            questionList.add(question27);

            Question question28 = new Question("28", "You can only send one attachment per e-mail message.", "False","");
            questionList.add(question28);

            Question question29 = new Question("29", "It is impossible to send a worm or virus over the Internet using in attachment.", "False","");
            questionList.add(question29);

            Question question30 = new Question("30", "All attachment are safe.", "False","");
            questionList.add(question30);
    db.addQuestions(questionList);

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view =  inflater.inflate(R.layout.fragment_recycler_view, container, false);

        submit_button =  root_view.findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
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
                        //Which column you want to exprort
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

        recyclerView = root_view.findViewById(R.id.recycler_view_id);

        db = new DataBase(getActivity());

        if(db.getDatabaseCount() == 0)
        {
            prepareQuestionData();
        }
        else
        {
         questionList = db.getAllData();
        }

        qAdapter = new QuestionAdapter(questionList);

        RecyclerView.LayoutManager myLayout=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(myLayout);

        //recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(qAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Question question = questionList.get(position);
                pos = position;
                s = question.getQuestion().toString();
                s1 = question.getId().toString();
                String s2 = " - ";
                s=s1+s2+s;
                DataShowFrag dsf = new DataShowFrag();
                fragmentCall(dsf);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return root_view;
    }

    public void fragmentCall(Fragment fr)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fr).addToBackStack(fr.getClass().getName());
        fragmentTransaction.commit();
    }
    // TODO: Rename method, update argument and hook method into UI event
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
        void onFragmentInteraction(Uri uri);
    }
}