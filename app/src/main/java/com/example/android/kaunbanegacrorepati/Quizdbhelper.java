package com.example.android.kaunbanegacrorepati;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.kaunbanegacrorepati.Quizcontract.*;

import java.util.ArrayList;
import java.util.List;

public class Quizdbhelper<Private> extends SQLiteOpenHelper {

    private static final  String  DATABASE_NAME ="KBC.db";
    private static final int Database_Version =1;
    private  SQLiteDatabase db;
    public Quizdbhelper( Context context) {
        super(context, DATABASE_NAME, null ,Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        //db = this.getWritableDatabase();
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Questiontable.TABLE_NAME + " ( " +
                Questiontable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Questiontable.Question_Col + " TEXT, " +
                Questiontable.OPTION_1 + " TEXT, " +
                Questiontable.OPTION_2 + " TEXT, " +
                Questiontable.OPTION_3 + " TEXT, " +
                Questiontable.OPTION_4 + " TEXT, " +
                Questiontable.CORRECT + " INTEGER, " +
                Questiontable.DIFFICULTY_COL + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestiontable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS"+ Questiontable.TABLE_NAME);
      onCreate(db);

    }

    private void fillQuestiontable()
    {
        Question question1 = new Question( "EASY : WHAT IS SQL ?"  ,
                "language", "database ","RElational database" , "software", 2, Question.DIFFICULTY_EASY ) ;
        Question question2 = new Question( "MEDIUM : WHAT IS MYSQL ?"  ,
                "language", "database ","RElational database" , "software", 2, Question.DIFFICULTY_MEDIUM ) ;
        Question question3 = new Question( "MEDIUM : WHAT IS NoSQL ?"  ,
                "language", "database ","RElational database" , "software", 2, Question.DIFFICULTY_MEDIUM) ;
        Question question4 = new Question( "HARD : WHAT IS Firebase ?"  ,
                "language", "database ","RElational database" , "software", 2, Question.DIFFICULTY_HARD ) ;
        Question question5 = new Question( "HARD : WHAT IS SQLite ?"  ,
                "language", "database ","RElational database" , "software", 2, Question.DIFFICULTY_HARD ) ;
        Question question6 = new Question( "HARD : WHAT IS Android Studio ?"  ,
                "language", "database ","RElational database" , "software", 4, Question.DIFFICULTY_HARD ) ;


        addDatabaseQuestions (question1);
        addDatabaseQuestions (question2);
        addDatabaseQuestions (question3);
        addDatabaseQuestions (question4);
        addDatabaseQuestions (question5);
        addDatabaseQuestions (question6);

    }
    private void addDatabaseQuestions(Question question)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Questiontable.Question_Col , question.getQuestion());
        contentValues.put(Questiontable.OPTION_1 , question.getOption1());
        contentValues.put(Questiontable.OPTION_2 , question.getOption2());
        contentValues.put(Questiontable.OPTION_3 , question.getOption3());
        contentValues.put(Questiontable.OPTION_4 , question.getOption4());
        contentValues.put(Questiontable.CORRECT , question.getCorrect());
        db.insert(Questiontable.TABLE_NAME,null,contentValues);
    }
    public ArrayList<Question>  getDatabaseQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Questiontable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(1));
                question.setOption1(c.getString(2));
                question.setOption2(c.getString(3));
                question.setOption3(c.getString(4));
                question.setOption4(c.getString(5));
                question.setCorrect(c.getInt(6));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + Questiontable.TABLE_NAME +
                " WHERE " + Questiontable.DIFFICULTY_COL + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(1));
                question.setOption1(c.getString(2));
                question.setOption2(c.getString(3));
                question.setOption3(c.getString(4));
                question.setOption3(c.getString(5));
                question.setCorrect(c.getInt(6));
                question.setDifficulty(c.getString(7));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }



}
