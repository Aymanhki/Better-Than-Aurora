package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DropCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DropCourseFragment extends Fragment {

    private ListView toDropSectionsList;
    private StudentSectionAdapter studentSectionAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DropCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drop_a_course.
     */
    // TODO: Rename and change types and number of parameters
    public static DropCourseFragment newInstance(String param1, String param2) {
        DropCourseFragment fragment = new DropCourseFragment();
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
        return inflater.inflate(R.layout.fragment_drop_a_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Student currentUser = (Student) new AccessUsers().getCurrentUser();
        ArrayList<StudentSection> sections = new AccessStudentSections().getStudentSectionList(currentUser.getID(), true);

        studentSectionAdapter = new StudentSectionAdapter(getContext(), R.layout.student_section_list_item, sections);
        toDropSectionsList = view.findViewById(R.id.enrolled_sections_in_drop_a_course_list_fragment);
        toDropSectionsList.setAdapter(studentSectionAdapter);
        NavController navController = NavHostFragment.findNavController(this);
        toDropSectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navController.navigate(DropCourseFragmentDirections.actionDropACourseToConfirmDroppingACourse(sections.get(i)));
            }
        });

    }
}