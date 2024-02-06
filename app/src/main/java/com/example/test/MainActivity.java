package com.example.test;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.*;

import android.widget.Button;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button addEvent;
    private Button toDo;

    private static ArrayList<Event> masterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static MainRecyclerViewAdapter adapter;

    //Plan to scan through the .txt and put data into

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEvent = findViewById(R.id.add_event);
        toDo = findViewById(R.id.to_do_button_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MainRecyclerViewAdapter(this, masterList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void addEvent(View view) {
        View addCourseButton = findViewById(R.id.button1);
        View addAssignmentButton = findViewById(R.id.button2);
        View addExamButton = findViewById(R.id.button3);
        View addEventButton = findViewById(R.id.add_event);

        addEventButton.setVisibility(View.INVISIBLE);
        addCourseButton.setVisibility(View.VISIBLE);
        addAssignmentButton.setVisibility(View.VISIBLE);
        addExamButton.setVisibility(View.VISIBLE);

    }

    public void addCourse(View view) {

        View addCourseButton = findViewById(R.id.button1);
        View addAssignmentButton = findViewById(R.id.button2);
        View addExamButton = findViewById(R.id.button3);

        addCourseButton.setVisibility(View.INVISIBLE);
        addAssignmentButton.setVisibility(View.INVISIBLE);
        addExamButton.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
        startActivity(intent);

    }

    public void addAssignment(View view) {
        View addCourseButton = findViewById(R.id.button1);
        View addAssignmentButton = findViewById(R.id.button2);
        View addExamButton = findViewById(R.id.button3);

        addCourseButton.setVisibility(View.INVISIBLE);
        addAssignmentButton.setVisibility(View.INVISIBLE);
        addExamButton.setVisibility(View.INVISIBLE);


        startActivity(new Intent(MainActivity.this, AddAssignmentActivity.class));

    }

    public void addExam(View view) {
        View addCourseButton = findViewById(R.id.button1);
        View addAssignmentButton = findViewById(R.id.button2);
        View addExamButton = findViewById(R.id.button3);

        addCourseButton.setVisibility(View.INVISIBLE);
        addAssignmentButton.setVisibility(View.INVISIBLE);
        addExamButton.setVisibility(View.INVISIBLE);


        startActivity(new Intent(MainActivity.this, AddAssessmentActivity.class));

    }

    public void goToToDo(View view) {
        startActivity(new Intent(MainActivity.this, ToDoListActivity.class));
    }

    @Override
    public void whenClicked(int pos) {
        if (masterList.get(pos) instanceof Course) {
            startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
        } else if (masterList.get(pos) instanceof Assignment) {
            startActivity(new Intent(MainActivity.this, AddAssignmentActivity.class));
        } else if (masterList.get(pos) instanceof Assessment) {
            startActivity(new Intent(MainActivity.this, AddAssessmentActivity.class));
        }
    }

    public static ArrayList<Event> getMasterList() {
        return masterList;
    }

    public static MainRecyclerViewAdapter getAdapter() {
        return adapter;
    }


}