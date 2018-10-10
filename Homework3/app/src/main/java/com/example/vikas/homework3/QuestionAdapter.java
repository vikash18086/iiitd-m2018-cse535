package com.example.vikas.homework3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{
    private List<Question> questionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView question;
        public MyViewHolder(View view)
        {
            super(view);
            question = (TextView) view.findViewById(R.id.ques);
        }
    }

    public QuestionAdapter(List<Question> questionsList)
    {
        this.questionsList = questionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Question question= questionsList.get(position);
        holder.question.setText("Question "+question.getId().toString());

    }

    @Override
    public int getItemCount()
    {
        return questionsList.size();
    }

}
