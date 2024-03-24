package com.example.test;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> dataArrayList;
    private final RecyclerViewInterface recyclerViewInterface;

    public CourseRecyclerViewAdapter(Context context, ArrayList<Event> dataSet, RecyclerViewInterface r) {
        this.context = context;
        this.dataArrayList = SeeCoursesActivity.getMasterList();
        this.recyclerViewInterface = r;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameAndSection;
        private TextView date;
        private TextView location;
        private TextView professor;
        private TextView time;
        private TextView days;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            courseNameAndSection = view.findViewById(R.id.rv_courseName);
            date = view.findViewById(R.id.rv_courseDate);
            location = view.findViewById(R.id.rv_courseLocation);
            professor = view.findViewById(R.id.rv_course_professorANDsection);
            time = view.findViewById(R.id.rv_courseTime);
            days = view.findViewById(R.id.rv_course_meetDays);

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

            view.findViewById(R.id.editItemButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddCourseActivity.class);

                    intent.putExtra("pos", getAdapterPosition());
                    startActivity(v.getContext(), intent, null);
                }
            });

            view.findViewById(R.id.deleteItemButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeCoursesActivity.getMasterList().remove(getAdapterPosition());
                    SeeCoursesActivity.itemRemovedCourse(getAdapterPosition());
                }
            });
        }

        public TextView getCourseName() {
            return courseNameAndSection;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getLocation() {
            return location;
        }

        public TextView getProfessorOrTitle() {
            return professor;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getDays() {
            return days;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_row_layout_course, viewGroup, false);

        return new ViewHolder(view, recyclerViewInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        Event event = dataArrayList.get(position);
        viewHolder.getCourseName().setText(event.getCourseName() + " " + ((Course) (event)).getClassSection());
        viewHolder.getDate().setText((String) ("" + event.getMonth() + "/" + event.getDay() + "/" + event.getYear()));
        viewHolder.getLocation().setText(((Course) (event)).getBuilding_AND_room());
        viewHolder.getProfessorOrTitle().setText(((Course) (event)).getProfessor());
        viewHolder.getTime().setText("" + ((Course) (event)).getStartHour() + ":" + String.format("%02d", ((Course) (event)).getStartMinute()));
        viewHolder.getDays().setText("Meets:" + ((Course) (event)).getDaysOfWeekSetString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

}
