package com.example.vikas.homework3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_db";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Question.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Question.TABLE_NAME);
        onCreate(db);
    }

    void addQuestions(List<Question> q)
    {
        for (int j = 0; j < q.size();j++) {
            insertInDatabase(q.get(j));
    }

    }

    public long insertInDatabase(Question ques) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Question.COLUMN_Id,ques.getId());
        values.put(Question.COLUMN_Question,ques.getQuestion());
        values.put(Question.COLUMN_Answer,ques.getAnswer());
        values.put(Question.COLUMN_Youranswer,ques.getYouranswer());
        long id = db.insert(Question.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Question getDatabase(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Question.TABLE_NAME, new String[]{Question.COLUMN_Id, Question.COLUMN_Question, Question.COLUMN_Answer, Question.COLUMN_Youranswer},
                Question.COLUMN_Id + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Question note = new Question(
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Id)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Question)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Answer)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Youranswer)));
        cursor.close();
        return note;
    }

    public Question getAnswer(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Question.TABLE_NAME, new String[]{Question.COLUMN_Id, Question.COLUMN_Youranswer},
                Question.COLUMN_Id + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Question note = new Question(
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Id)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Question)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Answer)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_Youranswer)));
        cursor.close();
        return note;
    }

    public List<Question> getAllData()
    {
        List<Question> q1 = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Question.TABLE_NAME + " ORDER BY " +
                Question.COLUMN_Id ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                Question q2 = new Question();
                q2.setId(cursor.getString(cursor.getColumnIndex(Question.COLUMN_Id)));
                q2.setQuestion(cursor.getString(cursor.getColumnIndex(Question.COLUMN_Question)));
                q2.setAnswer(cursor.getString(cursor.getColumnIndex(Question.COLUMN_Answer)));
                q2.setYouranswer(cursor.getString(cursor.getColumnIndex(Question.COLUMN_Youranswer)));
                q1.add(q2);
            } while (cursor.moveToNext());
        }
        db.close();
        return q1;
    }
    public int getDatabaseCount()
    {
        String countQuery = "SELECT  * FROM " + Question.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateDatabase(Question q1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Question.COLUMN_Youranswer, q1.getYouranswer());
        return db.update(Question.TABLE_NAME, values, Question.COLUMN_Id + "=?",
                new String[]{String.valueOf(q1.getId())});
    }
}