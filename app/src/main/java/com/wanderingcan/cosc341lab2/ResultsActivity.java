package com.wanderingcan.cosc341lab2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_NAME = "NAME";
    private static final String KEY_STUDENT_NUMBER = "STUDENT_NUMBER";
    private static final String KEY_DIVISION = "DIVISION";
    private static final String KEY_GENDER = "GENDER";

    private String name;
    private String studentNumber;
    private String division;
    private String gender;

    public static void start(Context context, String name, String studentNumber, String division,
                             String gender) {
        Intent starter = new Intent(context, ResultsActivity.class);
        starter.putExtra(KEY_NAME, name);
        starter.putExtra(KEY_STUDENT_NUMBER, studentNumber);
        starter.putExtra(KEY_DIVISION, division);
        starter.putExtra(KEY_GENDER, gender);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getExtras();

        TextView textView = findViewById(R.id.first_name);
        textView.setText(name);

        textView = findViewById(R.id.student_number);
        textView.setText(studentNumber);

        textView = findViewById(R.id.division);
        textView.setText(division);

        textView = findViewById(R.id.gender);
        textView.setText(gender);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void getExtras(){
        Intent intent = getIntent();
        name = intent.getStringExtra(KEY_NAME);
        studentNumber = intent.getStringExtra(KEY_STUDENT_NUMBER);
        division = intent.getStringExtra(KEY_DIVISION);
        gender = intent.getStringExtra(KEY_GENDER);
    }
}
