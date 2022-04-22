package com.group_15.bta.objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group_15.bta.R;

import java.util.ArrayList;
/**
 * CourseListAdapter
 * used to display course list
 */
public class CourseListAdapter extends ArrayAdapter<Course> {

    private final Context mainContext;
    private final int resource;

    public CourseListAdapter(Context context, int resource, ArrayList<Course> courses) {
        super(context, resource, courses);
        mainContext = context;
        this.resource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id = getItem(position).getID();
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String credit = getItem(position).getCreditHours() + " Credit Hours";

        LayoutInflater inflater = LayoutInflater.from(mainContext);
        convertView = inflater.inflate(resource, parent, false);

        TextView courseID = convertView.findViewById(R.id.course_code_list_item);
        TextView courseTitle = convertView.findViewById(R.id.course_title_list_item);
        TextView courseDescription = convertView.findViewById(R.id.course_description_list_item);
        TextView courseCredits = convertView.findViewById(R.id.course_credit_list_item);
        courseID.setText(id);
        courseTitle.setText(title);
        courseDescription.setText(description);
        courseCredits.setText(credit);

        return convertView;
    }
}
