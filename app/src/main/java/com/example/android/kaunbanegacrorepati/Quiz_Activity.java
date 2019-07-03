package com.example.android.kaunbanegacrorepati;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz_Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ=1;
    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";

    private TextView textViewHighScore;
    private  int highscore;

    Button mystartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);
        textViewHighScore= findViewById(R.id.high_score);
        loadHighsocre();
        initialize();
    }

    protected void initialize() {
        mystartButton=(Button)findViewById(R.id.button_start_quiz);
        mystartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Quiz_Activity.this,MainActivity.class);
                startActivityForResult(intent , REQUEST_CODE_QUIZ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_QUIZ)
        { if(resultCode==RESULT_OK){
             int score  = data.getIntExtra(MainActivity.EXTRA_SCORE,0     );
             if(score >highscore) {
                 updateHighSCore(score);
             }
        }

        }
    }


    private  void loadHighsocre()
    {    SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
         highscore = prefs.getInt(KEY_HIGHSCORE,0);
    }
    private  void  updateHighSCore(int highscoreNew)
    {
        highscore=highscoreNew;
        textViewHighScore.setText("High score: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();

    }



}
