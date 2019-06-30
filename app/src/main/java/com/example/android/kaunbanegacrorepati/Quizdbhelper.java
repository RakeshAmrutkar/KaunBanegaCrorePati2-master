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
    private  SQLiteDatabase db;
    public Quizdbhelper( Context context) {
        super(context, DATABASE_NAME, null ,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        //db = this.getWritableDatabase();
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Questiontable.TABLE_NAME + " ( " +
                Questiontable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Questiontable.OPTION_1 + " TEXT, " +
                Questiontable.OPTION_2 + " TEXT, " +
                Questiontable.OPTION_3 + " TEXT, " +
                Questiontable.OPTION_4 + " TEXT, " +
                Questiontable.CORRECT + " INTEGER" +
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
      Question question1 = new Question( "WHAT IS SQL ?"  , "  language", "database ","RElational database" , "software", 2  ) ;
       addDatabaseQuestions (question1);
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
    public List<Question>  getDatabaseQuestions(){
        List<Question> questions= new ArrayList<>();
        db=getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Questiontable.TABLE_NAME, null );
        if(cursor.moveToFirst()){
            do{
                Question question= new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(Questiontable.Question_Col)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(Questiontable.OPTION_1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(Questiontable.OPTION_2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(Questiontable.OPTION_3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(Questiontable.OPTION_4)));
                question.setCorrect(cursor.getInt(cursor.getColumnIndex(Questiontable.CORRECT)));
                questions.add(question);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

}
