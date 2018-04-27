package com.example.sherry.educationhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class HomeActivity extends AppCompatActivity {
    private Button Student;
    private Button Tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Tutor = (Button) findViewById(R.id.btt);

        Student =(Button)findViewById(R.id.bts) ;






        Tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ThirdActivity.class);
                startActivity(intent);

            }
        });

        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
    }}



