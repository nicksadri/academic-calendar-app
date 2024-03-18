package com.example.test;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.*;

import android.widget.Button;

public class SeeCoursesActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button addEvent;
    private Button toDo;

    private static ArrayList<Event> masterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static MainRecyclerViewAdapter adapter;

    //Plan to scan through the .txt and put data into

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_view);

        toDo = findViewById(R.id.to_do_button_main);

        recyclerView = findViewById(R.id.coursesRecyclerView);
        adapter = new MainRecyclerViewAdapter(this, masterList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void addCourse(View view) {
        startActivity(new Intent(SeeCoursesActivity.this, AddCourseActivity.class));
    }

    public void seeAssignments(View view) {
        startActivity(new Intent(SeeCoursesActivity.this, SeeAssignmentsActivity.class));
    }

    public void seeExams(View view) {
        startActivity(new Intent(SeeCoursesActivity.this, SeeExamsActivity.class));
    }

    public void goToToDo(View view) {
        startActivity(new Intent(SeeCoursesActivity.this, ToDoListActivity.class));
    }

    @Override
    public void whenClicked(int pos) {
        Intent intent1 = new Intent(SeeCoursesActivity.this, AddCourseActivity.class);
        intent1.putExtra("pos", pos);
        startActivity(intent1);
        masterList.remove(pos);
    }

    public static ArrayList<Event> getMasterList() {
        return masterList;
    }

    public static MainRecyclerViewAdapter getAdapter() {
        return adapter;
    }


}