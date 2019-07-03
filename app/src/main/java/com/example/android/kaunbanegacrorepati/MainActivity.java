package com.example.android.kaunbanegacrorepati;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    List<Question> questionList= new ArrayList<Question>();

    private ColorStateList textColorDefaultRb;
    private int questionCounter;
    private int questionCounterTotal;
    private Question CurrentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuestion = findViewById(R.id.text_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_question_count);
        textViewCountDown = findViewById(R.id.timer_id);
        rbGroup = findViewById(R.id.radio_group_id);
        rb1 = findViewById(R.id.radio_button_1);
        rb2 = findViewById(R.id.radio_button_2);
        rb3 = findViewById(R.id.radio_button_3);
        rb4 = findViewById(R.id.radio_button_4);
        buttonConfirmNext = findViewById(R.id.button_confirm);

        Quizdbhelper myQuiz = new Quizdbhelper(this);
        questionList= myQuiz.getDatabaseQuestions();
        textColorDefaultRb= rb1.getTextColors();
        questionCounterTotal= questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()||rb4.isChecked() ){
                        checkAnswer();
                    }else{
                        Toast.makeText(MainActivity.this,"Please Select an answer",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }

            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbchecked = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerGiven = rbGroup.indexOfChild(rbchecked)+1;
        if(answerGiven==CurrentQuestion.getCorrect()){
            score++;
            textViewScore.setText("Score :"+ score);
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch(CurrentQuestion.getCorrect()){
            case 1: rb1.setTextColor(Color.GREEN);
                    textViewQuestion.setText("Sahi Jawab 1");
                    break;
            case 2: rb2.setTextColor(Color.GREEN);
                    textViewQuestion.setText("Sahi Jawab 2");
                    break;
            case 3: rb3.setTextColor(Color.GREEN);
                    textViewQuestion.setText("Sahi Jawab 3");
                    break;
            case 4: rb4.setTextColor(Color.GREEN);
                    textViewQuestion.setText("Sahi Jawab 4");
                    break;

        }
        if(questionCounter<questionCounterTotal){
            buttonConfirmNext.setText("NEXT");
        }else{
            buttonConfirmNext.setText("FINISH");
        }
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCounterTotal) {
            CurrentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(CurrentQuestion.getQuestion());
            rb1.setText(CurrentQuestion.getOption1());
            rb2.setText(CurrentQuestion.getOption2());
            rb3.setText(CurrentQuestion.getOption3());
            rb4.setText(CurrentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCounterTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent resultintent  = new Intent();
        resultintent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultintent);
        finish();
    }
}
