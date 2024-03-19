package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignmentRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> dataArrayList;
    private final RecyclerViewInterface recyclerViewInterface;

    public AssignmentRecyclerViewAdapter(Context context, ArrayList<Event> dataSet, RecyclerViewInterface r) {
        this.context = context;
        this.dataArrayList = SeeAssignmentsActivity.getMasterList();
        this.recyclerViewInterface = r;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView date;
        private TextView location;
        private TextView professorOrTitle;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            courseName = view.findViewById(R.id.courseName);
            date = view.findViewById(R.id.date);
            location = view.findViewById(R.id.location);
            professorOrTitle = view.findViewById(R.id.professorOrTitle);
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

        public TextView getLocation() {
            return location;
        }

        public TextView getProfessorOrTitle() {
            return professorOrTitle;
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

        Event event = dataArrayList.get(position);
        viewHolder.getCourseName().setText(event.getCourseName());
        viewHolder.getDate().setText((String) ("" + event.getMonth() + "/" + event.getDay()));
        if (event instanceof Course) {
            viewHolder.getLocation().setText(((Course) (event)).getBuilding_AND_room());
        }
        if (event instanceof Assessment) {
            viewHolder.getLocation().setText(((Assessment) (event)).getBuilding_AND_room());
        }
        if (event instanceof Course) {
            viewHolder.getProfessorOrTitle().setText(((Course) (event)).getProfessor());
        }
        if (event instanceof Assignment) {
            viewHolder.getProfessorOrTitle().setText(((Assignment) (event)).getAssignmentTitle());
        }
        if (event instanceof Assessment) {
            viewHolder.getProfessorOrTitle().setText(((Assessment) (event)).getTestTitle());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

}
