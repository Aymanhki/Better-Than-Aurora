package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.objects.StudentSectionAdapter;

import java.util.ArrayList;


public class DropCourseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drop_a_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Student currentUser = (Student) new AccessUsers().getCurrentUser();
        ArrayList<StudentSection> sections = new AccessStudentSections().getStudentSectionList(currentUser.getID(), true);

        StudentSectionAdapter studentSectionAdapter = new StudentSectionAdapter(getContext(), R.layout.student_section_list_item, sections);
        ListView toDropSectionsList = view.findViewById(R.id.enrolled_sections_in_drop_a_course_list_fragment);
        toDropSectionsList.setAdapter(studentSectionAdapter);
        NavController navController = NavHostFragment.findNavController(this);
        toDropSectionsList.setOnItemClickListener((adapterView, view1, i, l) -> navController.navigate(DropCourseFragmentDirections.actionDropACourseToConfirmDroppingACourse(sections.get(i))));

    }
}