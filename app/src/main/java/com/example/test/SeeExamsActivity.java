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

public class SeeExamsActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button addEvent;

    private static ArrayList<Event> masterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static ExamRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exams_view);

        Log.d("status", "initialize data");

        recyclerView = findViewById(R.id.examsRecyclerView);
        adapter = new ExamRecyclerViewAdapter(this, masterList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void addExam(View view) {
        startActivity(new Intent(SeeExamsActivity.this, AddExamActivity.class));
    }

    public void seeAssignments(View view) {
        startActivity(new Intent(SeeExamsActivity.this, SeeAssignmentsActivity.class));
    }

    public void seeCourses(View view) {
        startActivity(new Intent(SeeExamsActivity.this, SeeCoursesActivity.class));
    }

    public void goToToDo(View view) {
        startActivity(new Intent(SeeExamsActivity.this, ToDoListActivity.class));
    }

    @Override
    public void whenClicked(int pos) {
//        Intent intent3 = new Intent(SeeExamsActivity.this, AddExamActivity.class);
//        startActivity(intent3);
//        masterList.remove(pos);
    }

    public static ArrayList<Event> getMasterList() {
        return masterList;
    }

    public static ExamRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public static void itemRemovedExam(int pos) {
        adapter.notifyItemRemoved(pos);
    }


}