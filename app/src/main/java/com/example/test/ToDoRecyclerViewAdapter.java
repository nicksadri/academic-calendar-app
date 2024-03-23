package com.example.test;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ViewHolder> {
    private Context context;
//    private static ArrayList<Task> dataArrayList = new ArrayList<Task>();
    private final RecyclerViewInterface recyclerViewInterface;

    private TextView courseName;
    private TextView date;
    private TextView taskTitle;
    private TextView completeness;
    private Button deleteButton;
    private Button editButton;
    private Button completedButton;

    public ToDoRecyclerViewAdapter(Context context, ArrayList<Task> dataSet, RecyclerViewInterface r, Button edit, Button delete, Button complete) {
        this.context = context;
        //this.dataArrayList = dataSet;
        this.recyclerViewInterface = r;
        this.editButton = edit;
        this.deleteButton = delete;
        this.completedButton = complete;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            view.findViewById(R.id.editToDoButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddTaskActivity.class);

                    intent.putExtra("pos", getAdapterPosition());
                    startActivity(v.getContext(), intent, null);
//                    ToDoListActivity.updateToDoRecyclerView(getAdapterPosition());
                }
            });

            view.findViewById(R.id.deleteToDoItem).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Goes to data class and removes item from backing ArrayList
                    Data.deleteToDoAtPos(getAdapterPosition());
                    // Updates the RecyclerView after item has been deleted
                    ToDoListActivity.updateToDoRemovedRecyclerView(getAdapterPosition());
                }
            });

            view.findViewById(R.id.completedButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data.tasks.get(getAdapterPosition()).setCompleteness(!Data.tasks.get(getAdapterPosition()).getCompleteness());
                    ToDoListActivity.getToDoListAdapter().notifyDataSetChanged();
                    ((CheckBox) (view.findViewById(R.id.completedButton))).setChecked(!((CheckBox) (view.findViewById(R.id.completedButton))).isChecked());

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

        viewHolder.setIsRecyclable(false);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {

                    recyclerViewInterface.whenClicked(position);
                }
            }
        });

        if (ToDoListActivity.getFilterComplete().isChecked()) {
            if (Data.tasks.get(viewHolder.getAdapterPosition()).getCompleteness() == true) {
                viewHolder.itemView.setVisibility(View.GONE);
                viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        } else {
//            for (int i = 0; i < Data.tasks.size(); i++) {
//                viewHolder.itemView.setVisibility(View.VISIBLE);
//            }
        }


//        if (Data.tasks.get(viewHolder.getAdapterPosition()).getCompleteness() == true) {
//            ToDoListActivity.getCompletedButton().setChecked(true);
//        }


    }

//    public void filterComplete(View view) {
//        if (((CheckBox) view).isChecked()) {
//            for ( v : toDoListAdapter) {
//                if (t.getCompleteness() == true) {
//                    t.setVisibility(View.GONE);
//                    t.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//                }
//            }
//        } else {
//
//        }
//    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Data.tasks.size();
    }

    public void update(List<Task> data) {
        if (data != null) {
            ArrayList<Task> temp = new ArrayList<>();
            temp.addAll(data);
            Data.tasks.clear();
            Data.tasks.addAll(temp);
            notifyDataSetChanged();
        }
    }
}
