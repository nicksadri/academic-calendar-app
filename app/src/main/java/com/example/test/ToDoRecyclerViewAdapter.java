package com.example.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ViewHolder> {
    private Context context;
//    private static ArrayList<Task> dataArrayList = new ArrayList<Task>();
    private final RecyclerViewInterface recyclerViewInterface;

    private TextView courseName;
    private TextView date;
    private TextView taskTitle;
    private TextView completeness;

    public ToDoRecyclerViewAdapter(Context context, ArrayList<Task> dataSet, RecyclerViewInterface r) {
        this.context = context;
//        this.dataArrayList = dataSet;
        this.recyclerViewInterface = r;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }



    }

    public TextView getCourseName() {
        return courseName;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getTaskTitle() {
        return taskTitle;
    }

    public TextView getCompleteness() {
        return completeness;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.to_do_list_recycler_view_row_layout, viewGroup, false);


        courseName = view.findViewById(R.id.to_do_list_course_name);
        date = view.findViewById(R.id.to_do_list_date);
        taskTitle = view.findViewById(R.id.to_do_list_task_title);
        completeness = view.findViewById(R.id.to_do_list_completeness);

        return new ViewHolder(view, recyclerViewInterface);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d("dataArraylist", ""+Data.tasks.size());

        Task task = Data.tasks.get(position);
        getCourseName().setText(task.getCourseName());
        getDate().setText((String) ("" + task.getMonth() + "/" + task.getDay()));
        getTaskTitle().setText(task.getTaskTitle());
        getCompleteness().setText(task.getCompleteness() ? "complete" : "incomplete");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {

                    recyclerViewInterface.whenClicked(position);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Data.tasks.size();
    }
}
