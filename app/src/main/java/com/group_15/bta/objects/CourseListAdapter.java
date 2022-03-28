package com.group_15.bta.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group_15.bta.R;

import java.util.ArrayList;

public class CourseListAdapter extends ArrayAdapter<Course> {

    private Context mainContext;
    private int resource;

    public CourseListAdapter(Context context, int resource, ArrayList<Course> courses) {
        super(context, resource, courses);
        mainContext = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id = getItem(position).getID();
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String credit = getItem(position).getCreditHours() + " Credit Hours";

        LayoutInflater inflater = LayoutInflater.from(mainContext);
        convertView = inflater.inflate(resource, parent, false);

        TextView courseID = (TextView) convertView.findViewById(R.id.course_code_list_item);
        TextView courseTitle = (TextView) convertView.findViewById(R.id.course_title_list_item);
        TextView courseDescription = (TextView) convertView.findViewById(R.id.course_description_list_item);
        TextView courseCredits = (TextView) convertView.findViewById(R.id.course_credit_list_item);
        courseID.setText(id);
        courseTitle.setText(title);
        courseDescription.setText(description);
        courseCredits.setText(credit);

        return convertView;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            //TODO:Implement this method.
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
        return filter;
    }
}
