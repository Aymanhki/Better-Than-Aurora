package com.group_15.bta.objects;

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

public class StudentSectionAdapter extends ArrayAdapter<StudentSection> {
    private Context mainContext;
    private int resource;

    public StudentSectionAdapter(Context context, int resource, ArrayList<StudentSection> sections) {
        super(context, resource, sections);
        mainContext = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String course = getItem(position).getSection().getAssociatedCourse();
        String location = getItem(position).getSection().getLocation();
        String instructor = getItem(position).getSection().getInstructor();
        String creditHours = getItem(position).getCreditHours() + " Credit Hours";
        String grade = getItem(position).getGrade();

        LayoutInflater inflater = LayoutInflater.from(mainContext);
        convertView = inflater.inflate(resource, parent, false);

        TextView courseName = (TextView) convertView.findViewById(R.id.course_name_in_graded_section_list_item);
        TextView locationName = (TextView) convertView.findViewById(R.id.location_in_graded_section_list_item);
        TextView instructorName = (TextView) convertView.findViewById(R.id.instructor_in_graded_section_list_item);
        TextView credits = (TextView) convertView.findViewById(R.id.credit_hours_in_graded_section_list_item);
        TextView gradeText = (TextView) convertView.findViewById(R.id.grade_in_graded_section_list_item);


        courseName.setText(courseName.getText() + "" + course);
        locationName.setText(locationName.getText() + "" + location);
        instructorName.setText(instructorName.getText() + "" + instructor);
        credits.setText(credits.getText() + "" + creditHours);
        gradeText.setText(gradeText.getText() + "" + grade);


        return convertView;
    }
}
