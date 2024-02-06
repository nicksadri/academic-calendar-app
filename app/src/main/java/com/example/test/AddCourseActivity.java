package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;

public class AddCourseActivity extends AppCompatActivity {
    //How do we let the user know that if they type regular event, they don't need classname or instructor??
    private Button done ;
    private Button cancel;



    private TextInputEditText courseName;
    private TextInputEditText date;
    private TextInputEditText time;
    private TextInputEditText location;
    private TextInputEditText professor;
    private TextInputEditText meetingDays;
    private TextInputEditText section;
    private Course courseToAdd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        courseName = findViewById(R.id.enter_course_course);
        date = findViewById(R.id.enter_date_course);
        time = findViewById(R.id.enter_time_course);
        location = findViewById(R.id.enter_location_course);
        professor = findViewById(R.id.enter_professor_course);
        meetingDays = findViewById(R.id.enter_meeting_days_course);
        section = findViewById(R.id.enter_section_course);

        done = findViewById(R.id.done_button_course);
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
                    String[] meetingDaysArray = meetingDays.getText().toString().split(",");

                    courseToAdd = new Course(year, month, day, courseName.getText().toString(),
                            professor.getText().toString(), section.getText().toString(),
                            location.getText().toString(), currStartHour, currStartMinute,
                            meetingDaysArray);

                    MainActivity.getMasterList().add(courseToAdd);
                    MainActivity.getAdapter().notifyDataSetChanged();
                    Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
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
                    String[] meetingDaysArray = meetingDays.getText().toString().split(",");

                    //checks if date is in the correct format otherwise pops up a toast
                    if (month < 0 || month > 12 || day < 0 || day > 31 || year < 2024) {
                        Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                        return false;
                    } else if (currStartHour < 0 || currStartMinute > 59) {
                        //checks if start time and end time is valid
                        Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT).show();
                        return false;
                    } else if (!validDaysofWeek()) {
                        Toast.makeText(getApplicationContext(), "Invalid Days Format", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    
                } catch (Exception e) {
                    //In case they type in the wrong format and we get a casting exception
                    Toast.makeText(getApplicationContext(), "Invalid Format", Toast.LENGTH_SHORT).show();
                    return false;
                }


                return true;
            }
            
            boolean validDaysofWeek() {
                try {
                    String currMeetingDays = meetingDays.getText().toString();
                    String[] currMeetingArr = currMeetingDays.split(",");
                    HashSet<String> validDays = new HashSet<>();
                    validDays.add("M");
                    validDays.add("T");
                    validDays.add("W");
                    validDays.add("R");
                    validDays.add("F");
                    for (int i = 0; i < currMeetingArr.length; i++) {
                        if (!validDays.contains(currMeetingArr[i])) {
                            return false;
                        }
                    }
                    return true;
                }
                catch(Exception e) {
                    return false;
                }
            }

        });
        //Still need to figure out thus button
        cancel = findViewById(R.id.cancel_button_course);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
            }
        });
    }

}