package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private Button addTaskButton;
    private Button returnButton;

    private static ArrayList<Task> taskMasterList = new ArrayList<>();
    private RecyclerView toDoListRecyclerView;


    private static ToDoRecyclerViewAdapter toDoListAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);

        addTaskButton = findViewById(R.id.add_task);
        returnButton = findViewById(R.id.return_button);

        toDoListRecyclerView = findViewById(R.id.recyclerViewForToDo);
        toDoListAdapter = new ToDoRecyclerViewAdapter(this, taskMasterList, this);

        toDoListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        toDoListRecyclerView.setAdapter(toDoListAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ARRAYSIZE",""+taskMasterList.size());
    }

    public void addTask(View view) {
        startActivity(new Intent(ToDoListActivity.this, AddTaskActivity.class));
    }

    public void returnHome(View view) {
        startActivity(new Intent(ToDoListActivity.this, MainActivity.class));
    }

    @Override
    public void whenClicked(int pos) {
        if (MainActivity.getMasterList().get(pos) instanceof Assignment) {
            startActivity(new Intent(ToDoListActivity.this, AddAssignmentActivity.class));
        } else if (MainActivity.getMasterList().get(pos) instanceof Assessment) {
            startActivity(new Intent(ToDoListActivity.this, AddAssessmentActivity.class));
        } else if (MainActivity.getMasterList().get(pos) instanceof Task) {
            startActivity(new Intent(ToDoListActivity.this, AddTaskActivity.class));
        }
    }

    public static ArrayList<Task> getTaskMasterList() {
        return taskMasterList;
    }

    public static ToDoRecyclerViewAdapter getToDoListAdapter() {
        return toDoListAdapter;
    }

    public static void sortByDate(ArrayList<Task> taskMasterList) { //CHECK
        for (int i = 1; i < taskMasterList.size(); i++) {
            Task temp = taskMasterList.get(i);
            int j = i - 1;
            while (j >= 0 && taskMasterList.get(j).compareTo(temp) > 0) {
                //the compare to method override in event
                taskMasterList.set(j + 1, taskMasterList.get(j));
                j--;
            }
            taskMasterList.set(j + 1, temp);
        }
    }

    public static void sortBytaskTitle(ArrayList<Task> taskMasterList) { //CHECK
        for (int i = 1; i < taskMasterList.size(); i++) {
            Task temp = taskMasterList.get(i);
            int j = i - 1;
            while (j >= 0 && taskMasterList.get(j).getTaskTitle().compareTo(temp.getTaskTitle()) > 0) {
                //Strings compareTo
                taskMasterList.set(j + 1, taskMasterList.get(j));
                j--;
            }
            taskMasterList.set(j + 1, temp);
        }
    }

}
