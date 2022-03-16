package com.group_15.bta.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.objects.StudentSectionAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmSectionDrop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmSectionDrop extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmSectionDrop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment confirm_dropping_a_course.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmSectionDrop newInstance(String param1, String param2) {
        ConfirmSectionDrop fragment = new ConfirmSectionDrop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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