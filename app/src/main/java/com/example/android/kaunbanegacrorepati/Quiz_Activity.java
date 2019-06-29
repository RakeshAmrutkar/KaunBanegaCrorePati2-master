package com.example.android.kaunbanegacrorepati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz_Activity extends AppCompatActivity {

    Button mystartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);
        initialize();
    }

    protected void initialize() {
        mystartButton=(Button)findViewById(R.id.button_start_quiz);
        mystartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Quiz_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
