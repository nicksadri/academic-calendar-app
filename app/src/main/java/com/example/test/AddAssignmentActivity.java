package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddAssignmentActivity extends AppCompatActivity {
    //How do we let the user know that if they type regular event, they don't need classname or instructor??
    private Button done;
    private Button cancel;

    private TextInputEditText courseName;
    private TextInputEditText date;
    private TextInputEditText assignmentTitle;
    private Assignment assignmentToAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        courseName = findViewById(R.id.enter_course_assign);
        date = findViewById(R.id.enter_date_assign);
        assignmentTitle = findViewById(R.id.enter_title_assign);

        done = findViewById(R.id.done_button_assign);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInputs()) {
                    String currDate = date.getText().toString();
                    String[] dateArr = currDate.split("/");
                    int month = Integer.valueOf(dateArr[0]);
                    int day = Integer.valueOf(dateArr[1]);
                    int year = Integer.valueOf(dateArr[2]);

                    assignmentToAdd = new Assignment(year, month, day, courseName.getText().toString(), assignmentTitle.getText().toString());

                    MainActivity.getMasterList().add(assignmentToAdd);
                    MainActivity.getAdapter().notifyDataSetChanged();
                    Intent intent = new Intent(AddAssignmentActivity.this, MainActivity.class);
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

                    //checks if date is in the correct format otherwise pops up a toast
                    if (month < 0 || month > 12 || day < 0 || day > 31 || year < 2024) {
                        Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
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
        cancel = findViewById(R.id.cancel_button_assign);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAssignmentActivity.this, MainActivity.class));
            }
        });
    }
}