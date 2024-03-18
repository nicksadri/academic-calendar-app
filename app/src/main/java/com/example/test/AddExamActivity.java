package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddExamActivity extends AppCompatActivity {
    private Button done;
    private Button cancel;

    private TextInputEditText courseName;
    private TextInputEditText date;
    private TextInputEditText time;
    private TextInputEditText location;
    private TextInputEditText assessTitle;
    private Assessment assessmentToAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assessment);
        courseName = findViewById(R.id.enter_course_assess);
        date = findViewById(R.id.enter_date_assess);
        time = findViewById(R.id.enter_time_assess);
        location = findViewById(R.id.enter_location_assess);
        assessTitle = findViewById(R.id.enter_title_assess);


        done = findViewById(R.id.done_button_assess);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInputs()) {
                    String currDate = date.getText().toString();
                    String[] dateArr = currDate.split("/");
                    int month = Integer.valueOf(dateArr[0]);
                    int day = Integer.valueOf(dateArr[1]);
                    int year = Integer.valueOf(dateArr[2]);

                    String currStart = time.getText().toString();
                    String[] currStartArr = currStart.split(":");
                    int currStartHour = Integer.valueOf(currStartArr[0]);
                    int currStartMinute = Integer.valueOf(currStartArr[1]);

                    assessmentToAdd = new Assessment(year, month, day, courseName.getText().toString(),
                            assessTitle.getText().toString(), location.getText().toString(),
                    currStartHour, currStartMinute);

                    SeeExamsActivity.getMasterList().add(assessmentToAdd);
                    SeeExamsActivity.getAdapter().notifyDataSetChanged();
                    Intent intent = new Intent(AddExamActivity.this, SeeExamsActivity.class);
                    startActivity(intent);
                }

            }

            boolean validInputs() { //Sees if the user inputs are valid
                try {
                    //gets day months and year from user text
                    String currDate = date.getText().toString();
                    String[] dateArr = currDate.split("/");
                    int month = Integer.valueOf(dateArr[0]);
                    int day = Integer.valueOf(dateArr[1]);
                    int year = Integer.valueOf(dateArr[2]);
                    //gets the hour and minute of time
                    String currStart = time.getText().toString();
                    String[] currStartArr = currStart.split(":");
                    int currStartHour = Integer.valueOf(currStartArr[0]);
                    int currStartMinute = Integer.valueOf(currStartArr[1]);

                    //checks if date is in the correct format otherwise pops up a toast
                    if (month < 0 || month > 12 || day < 0 || day > 31 || year < 2024) {
                        Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                        return false;
                    } else if (currStartHour < 0 || currStartMinute > 59) {
                        //checks if start time and end time is valid
                        Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } catch (Exception e) {
                    //In case they type in the wrong format and we get a casting exception
                    Toast.makeText(getApplicationContext(), "Invalid Format", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
        //Still need to figure out thus button
        cancel = findViewById(R.id.cancel_button_assess);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExamActivity.this, SeeExamsActivity.class));
            }
        });
    }
}
