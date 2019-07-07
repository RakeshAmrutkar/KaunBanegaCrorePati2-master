package com.example.android.kaunbanegacrorepati;

import android.provider.BaseColumns;

public  final class Quizcontract {
    private  Quizcontract()
    {    }
    public static  class Questiontable implements BaseColumns {
        public static final String TABLE_NAME =" quiz_questions";
        public static final String Question_Col =" question";
        public static final String OPTION_1 =" op1";
        public static final String OPTION_2 =" op2";
        public static final String OPTION_3 =" op3";
        public static final String OPTION_4 =" op4";
        public static final String CORRECT =" correct";
        public static final String DIFFICULTY_COL =" difficulty";




    }


}
