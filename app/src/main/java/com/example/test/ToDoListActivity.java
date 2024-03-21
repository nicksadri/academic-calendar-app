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
    private Button editToDoButton;
    private Button deleteToDoButton;
    private Button completedButton;
    private Button dateSortButton;

    //private static ArrayList<Task> taskMasterList = Data.tasks;
    private RecyclerView toDoListRecyclerView;


    private static ToDoRecyclerViewAdapter toDoListAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);

//        taskMasterList = new ArrayList<>();

        addTaskButton = findViewById(R.id.add_task);
        returnButton = findViewById(R.id.return_button);
        editToDoButton = findViewById(R.id.editToDoButton);
        deleteToDoButton = findViewById(R.id.deleteToDoItem);
        completedButton = findViewById(R.id.completedButton);
        this.dateSortButton = findViewById(R.id.sortByDateButton);


        toDoListRecyclerView = findViewById(R.id.recyclerViewForToDo);
        //EDITED HERE
        toDoListAdapter = new ToDoRecyclerViewAdapter(this, Data.tasks, this, editToDoButton, deleteToDoButton, completedButton);

        toDoListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        toDoListRecyclerView.setAdapter(toDoListAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        //EDITED HERE
        Log.d("ARRAYSIZE",""+Data.tasks.size());
        toDoListAdapter.notifyDataSetChanged();
    }

    public void addTask(View view) {
        startActivity(new Intent(ToDoListActivity.this, AddTaskActivity.class));
    }

    public void returnHome(View view) {
        Intent intent = new Intent(ToDoListActivity.this, SeeCoursesActivity.class);
//        intent.putExtra("tasks", Data.tasks);
        startActivity(intent);
    }

    public void dateSort(View view) {
        toDoListAdapter.update(Data.sortByDate());
    }

    public void nameSort(View view) {
        toDoListAdapter.update(Data.sortByTaskTitle());
    }

    // Updates the recyclerView after delete button is pressed
    public static void updateToDoRemovedRecyclerView(int pos) {
        toDoListAdapter.notifyItemRemoved(pos);
    }

    @Override
    public void whenClicked(int pos) {
//        Intent intent = new Intent(ToDoListActivity.this, AddTaskActivity.class);
//
//        intent.putExtra("pos", pos);
//        startActivity(intent);

//        if (MainActivity.getMasterList().get(pos) instanceof Assignment) {
//            startActivity(new Intent(ToDoListActivity.this, AddAssignmentActivity.class));
//        } else if (MainActivity.getMasterList().get(pos) instanceof Assessment) {
//            startActivity(new Intent(ToDoListActivity.this, AddAssessmentActivity.class));
//        } else if (MainActivity.getMasterList().get(pos) instanceof Task) {
//            startActivity(new Intent(ToDoListActivity.this, AddTaskActivity.class));
//        }
    }

    //EDITED HERE
    public static ArrayList<Task> getTaskMasterList() {
        return Data.tasks;
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

    public static void sortByTaskTitle(ArrayList<Task> taskMasterList) { //CHECK
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Status", "Destroyed");
    }

}
