package com.example.android.kaunbanegacrorepati;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.kaunbanegacrorepati.Quizcontract.*;
public class Quizdbhelper<Private> extends SQLiteOpenHelper {

    private static final  String  DATABASE_NAME ="KBC.db";
    private  SQLiteDatabase db;
    public Quizdbhelper( Context context) {
        super(context, DATABASE_NAME, null ,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db = this.getWritableDatabase();
        final String Query = " create table "+ Questiontable.TABLE_NAME +"(" + Questiontable._ID +"INTEGER PRIMARY KEY AUTOINCREMENT, "+
                 Questiontable.Question + "TEXT, " + Questiontable.OPTION_1 +"TEXT," + Questiontable.OPTION_2 +"TEXT,"+
                  Questiontable.OPTION_3 +"TEXT," + Questiontable.OPTION_4 +"TEXT,"+ Questiontable.CORRECT +"INTEGER," +")";
       db.execSQL(Query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS"+ Questiontable.TABLE_NAME);
      onCreate(db);
      fillQuestiontable();
    }

    private void fillQuestiontable()
    {
      Question question1 = new Question( "WHAT IS SQL ?"  , "  language", "database ","RElational database" , "software", 2  ) ;
       adddatabasequestions (question1);
    }
    private void adddatabasequestions(Question question)

    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Questiontable.Question , question.getQuestion());
        contentValues.put(Questiontable.OPTION_1 , question.getOption1());
        contentValues.put(Questiontable.OPTION_2 , question.getOption2());
        contentValues.put(Questiontable.OPTION_3 , question.getOption3());
        contentValues.put(Questiontable.OPTION_4 , question.getOption4());
        contentValues.put(Questiontable.CORRECT , question.getCorrect());
        db.insert(Questiontable.TABLE_NAME,null,contentValues);





    }
}
