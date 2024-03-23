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

public class SeeAssignmentsActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button addEvent;

    private static ArrayList<Event> masterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static AssignmentRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignments_view);

        Log.d("status", "initialize data");

        recyclerView = findViewById(R.id.assignmentsRecyclerView);
        adapter = new AssignmentRecyclerViewAdapter(this, masterList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void addAssignment(View view) {
        startActivity(new Intent(SeeAssignmentsActivity.this, AddAssignmentActivity.class));
    }

    public void seeExams(View view) {
        startActivity(new Intent(SeeAssignmentsActivity.this, SeeExamsActivity.class));
    }

    public void seeCourses(View view) {
        startActivity(new Intent(SeeAssignmentsActivity.this, SeeCoursesActivity.class));
    }

    public void goToToDo(View view) {
        startActivity(new Intent(SeeAssignmentsActivity.this, ToDoListActivity.class));
    }

    @Override
    public void whenClicked(int pos) {
//        Intent intent2 = new Intent(SeeAssignmentsActivity.this, AddAssignmentActivity.class);
//        startActivity(intent2);
//        masterList.remove(pos);
    }

    public static ArrayList<Event> getMasterList() {
        return masterList;
    }

    public static AssignmentRecyclerViewAdapter getAdapter() {
        return adapter;
    }
    public static void itemRemovedAssigment(int pos) {
        adapter.notifyItemRemoved(pos);
    }


}