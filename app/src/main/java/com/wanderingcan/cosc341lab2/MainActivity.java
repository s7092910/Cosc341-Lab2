package com.wanderingcan.cosc341lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText studentNumberView;
    private EditText firstNameView;
    private EditText lastNameView;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private CheckBox checkBox;
    private Button button;

    private String[] spinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentNumberView = findViewById(R.id.student_number);
        firstNameView = findViewById(R.id.first_name);
        lastNameView = findViewById(R.id.last_name);
        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.division);
        checkBox = findViewById(R.id.checkBox);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);

        spinnerText = getResources().getStringArray(R.array.divisions);
    }


    @Override
    public void onClick(View v) {
        String studentNumber = studentNumberView.getText().toString();
        String firstName = firstNameView.getText().toString();
        String lastName = lastNameView.getText().toString();
        String gender = getRadioButtonText();
        int spinnerSelectedItemPosition = spinner.getSelectedItemPosition();
        String division = spinnerText[spinnerSelectedItemPosition];
        boolean notRobot = checkBox.isChecked();

        boolean validInputs = validateInputs(studentNumber, firstName, lastName, notRobot);

        if(validInputs){
            ResultsActivity.start(this, firstName + " " + lastName, studentNumber,
                    division, gender);
        }
    }

    private String getRadioButtonText(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == -1) {
            return "";
        }
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }

    private boolean validateInputs(String studentNumber, String firstName, String lastName,
                                   boolean notRobot){
        boolean valid = validateText(studentNumber, 8,"Invalid Student Number");
        if(!valid) {
            return false;
        }

        valid = validateText(firstName, "No First Name Entered");
        if(!valid) {
            return false;
        }

        valid = validateText(lastName, "No Last Name Entered");
        if(!valid) {
            return false;
        }

        if(!notRobot) {
            Toast.makeText(this, "Make sure you are not a robot", Toast.LENGTH_SHORT).show();
        }

        return notRobot;
    }

    private boolean validateText(String textToValidate, String errorMessage){
        boolean valid = !TextUtils.isEmpty(textToValidate);
        if(!valid) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    private boolean validateText(String textToValidate, int requiredLength, String errorMessage){
        boolean valid = !TextUtils.isEmpty(textToValidate) && textToValidate.length() == requiredLength;
        if(!valid) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }

        return valid;
    }
}
