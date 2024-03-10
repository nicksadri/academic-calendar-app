package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;

public class AddTaskActivity extends AppCompatActivity {
    private Button done;
    private Button cancel;
    private Button addTask;

    private TextInputEditText courseName;
    private TextInputEditText date;
    private TextInputEditText taskTitle;
    private Task taskToAdd;

    private boolean canceledEdit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        Intent intent = getIntent();

//        int pos = intent.getIntExtra("pos", -1);
//        Log.d("Pos", ""+pos);

        courseName = findViewById(R.id.enter_course_task);
        date = findViewById(R.id.enter_date_task);
        taskTitle = findViewById(R.id.enter_title_task);


        if (pos != -1) {
            Task task = Data.tasks.get(pos);
            taskTitle.setText(task.getTaskTitle());
            courseName.setText(task.getCourseName());
            date.setText("" + task.getMonth() + "/" + task.getDay() + "/" + task.getYear());
            Data.tasks.remove(task);
        }


        done = findViewById(R.id.done_button_task);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInputs()) {
                    String currDate = date.getText().toString();
                    String[] dateArr = currDate.split("/");
                    int month = Integer.valueOf(dateArr[0]);
                    int day = Integer.valueOf(dateArr[1]);
                    int year = Integer.valueOf(dateArr[2]);


                    taskToAdd = new Task(year, month, day, courseName.getText().toString(), taskTitle.getText().toString(), false);
                    Data.tasks.add(taskToAdd);

//                    ToDoListActivity.getToDoListAdapter().notifyDataSetChanged();
//                    Intent intent = new Intent(AddTaskActivity.this, ToDoListActivity.class);
//                    startActivity(intent);
                    finish();
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
        cancel = findViewById(R.id.cancel_button_task);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(new Intent(AddTaskActivity.this, MainActivity.class));
            }
        });

    }

}
