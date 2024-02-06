package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Task> dataArrayList = new ArrayList<Task>();
    private final RecyclerViewInterface recyclerViewInterface;

    public ToDoRecyclerViewAdapter(Context context, ArrayList<Task> dataSet, RecyclerViewInterface r) {
        this.context = context;
        this.dataArrayList = dataSet;
        this.recyclerViewInterface = r;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView date;
        private TextView taskTitle;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            courseName = view.findViewById(R.id.enter_course_task);
            date = view.findViewById(R.id.enter_date_task);
            taskTitle = view.findViewById(R.id.enter_title_task);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.whenClicked(pos);
                        }
                    }
                }
            });
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


    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_row_layout, viewGroup, false);

        return new ViewHolder(view, recyclerViewInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        Task task = dataArrayList.get(position);
        viewHolder.getCourseName().setText(task.getCourseName());
        viewHolder.getDate().setText((String) ("" + task.getMonth() + "/" + task.getDay()));
        viewHolder.getTaskTitle().setText(task.getTaskTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
}
