package com.group_15.bta.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.business.Calculate;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.objects.StudentSectionAdapter;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    ArrayList<StudentSection> studentSections;
    String gpa;
    String creditHours;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentSections = new AccessStudentSections().getStudentSectionList(new AccessUsers().getCurrentUser().getID(), false);
        if(Calculate.gpa(studentSections)!=null)
        {
            gpa = Calculate.gpa(studentSections).toString();
        }
        else
        {
            gpa="0.0";
        }


        creditHours = Calculate.creditHours(studentSections);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView gpaBox = view.findViewById(R.id.gpa_history_text);
        TextView creditHoursBox = view.findViewById(R.id.credit_hours_history_text);
        ListView completedCourses = view.findViewById(R.id.completed_courses_student_section_list);
        TextView noCourses = view.findViewById(R.id.completed_courses_student_section_list_empty_text);
        if(!studentSections.isEmpty())
        {
            gpaBox.setText(gpaBox.getText()+" "+ gpa);
            creditHoursBox.setText(creditHoursBox.getText()+" "+ creditHours);
            completedCourses.setVisibility(View.VISIBLE);
            noCourses.setVisibility(View.GONE);
            completedCourses.setAdapter(new StudentSectionAdapter(getContext(), R.layout.student_section_list_item, studentSections));
        }
        else
        {
            gpaBox.setText(gpaBox.getText()+" 0.0");
            creditHoursBox.setText(creditHoursBox.getText()+" 0.0");
            completedCourses.setVisibility(View.GONE);
            noCourses.setVisibility(View.VISIBLE);
        }
    }
}