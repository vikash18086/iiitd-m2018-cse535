package com.example.vikas.homework3;

public class Question {
    private String question, answer, id , youranswer;

    public static final String TABLE_NAME = "quiz";
    public static final String COLUMN_Id = "qid";
    public static final String COLUMN_Question = "qquestion";
    public static final String COLUMN_Answer = "qanswer";
    public static final String COLUMN_Youranswer = "qyouranswer";


    private int qid;
    private String qquestion;
    private String qanswer;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_Id + " INTEGER PRIMARY KEY,"
                    + COLUMN_Question + " TEXT,"
                    + COLUMN_Answer + " TEXT,"
                    + COLUMN_Youranswer + " TEXT"
                    + ")";

    public Question() {

    }


    public Question(String id, String question, String answer, String youranswer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.youranswer = youranswer;

    }
    public String getId()

    {
        return id;
    }
    public void setId(String id)

    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public String getYouranswer()
    {
        return  youranswer;
    }
    public void setYouranswer(String youranswer)
    {
        this.youranswer = new String(youranswer);
    }

}
