package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group_15.bta.R;


public class StudentCoursesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StudentAccountActivity.setFragmentNavigationButton(view, R.id.add_or_drop_courses_btn, R.id.action_student_courses_to_add_or_drop_courses);
        StudentAccountActivity.setFragmentNavigationButton(view, R.id.view_courses_btn, R.id.action_student_courses_to_viewCategories);
        StudentAccountActivity.setFragmentNavigationButton(view, R.id.history_btn, R.id.action_student_courses_to_history);
    }
}