package com.example.vikas.homework2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class FragmentNew_A2_MT18086 extends Fragment {
    Button play,pause,stop,download,internet;
    BroadcastReceiver receiver;
    ListView lsview;
    ArrayAdapter adapter;
    static Uri uriList[];
    static int index;
    ArrayList<String> songList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_song_list, container, false);


        play = parentView.findViewById(R.id.play);

        stop = parentView.findViewById(R.id.stop);
        download = parentView.findViewById(R.id.download);
        internet= parentView.findViewById(R.id.internet);
        lsview=parentView.findViewById(R.id.listsong);

        Field fs[]=R.raw.class.getFields();
        uriList=new Uri[fs.length];
        songList = new ArrayList<String>();
        for(int i=0;i<fs.length;i++)
        {
            songList.add(fs[i].getName()+".mp3");
            uriList[i]=Uri.parse("android.resource://"+getActivity().getPackageName()+"/raw/"+fs[i].getName());
        }

        adapter =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,songList);
        lsview.setAdapter(adapter);

        lsview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                index=position;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SongService_A2_MT18086.class);
                getActivity().startService(intent);
            }
        });

        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), internetcheck_A2_MT18086.class);
                getActivity().startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),SongService_A2_MT18086.class);
                getActivity().stopService(intent);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DownloadIntentService_A2_MT18086.class);
                intent.putExtra("URL","http://faculty.iiitd.ac.in/~mukulika/");
                intent.putExtra("Filename","s1.mp3");
                getActivity().startService(intent);
            }
        });
        /*for receiving the Message for download*/
        receiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                String filepath = intent.getStringExtra("Filepath");
                int result = intent.getIntExtra("Result",-1);
                if (bundle != null) {
                    if(result== Activity.RESULT_OK){
                        Toast.makeText(getContext(),"Download Complete, URI="+filepath,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"Download failed",Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
/*
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/
        return parentView;
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter("com.example.vikas.homework2;"));
    }
}
