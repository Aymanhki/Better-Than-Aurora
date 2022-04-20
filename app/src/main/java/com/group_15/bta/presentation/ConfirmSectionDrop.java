package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.objects.StudentSectionAdapter;

import java.util.ArrayList;


public class ConfirmSectionDrop extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_dropping_a_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StudentSection selectedSection;
        selectedSection = ConfirmSectionDropArgs.fromBundle(requireArguments()).getCourseToBeDropped();
        ListView confirmSectionList = view.findViewById(R.id.confirm_section_to_be_dropped_list);
        ArrayList<StudentSection> selectedSections = new ArrayList<>();
        selectedSections.add(selectedSection);
        StudentSectionAdapter sectionsAdapted = new StudentSectionAdapter(getContext(), R.layout.student_section_list_item, selectedSections);
        confirmSectionList.setAdapter(sectionsAdapted);
        Student currentUser = (Student) new AccessUsers().getCurrentUser();

        NavController navController = NavHostFragment.findNavController(this);
        Button dropSectionBtn = (Button) view.findViewById(R.id.drop_section_btn);
        dropSectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentUser.deleteSection(selectedSection);
                Toast.makeText(getContext(), "Course Dropped", Toast.LENGTH_LONG).show();
                navController.navigate(R.id.action_confirm_dropping_a_course_to_student_home);
            }
        });

    }
}