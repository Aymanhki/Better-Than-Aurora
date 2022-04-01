package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group_15.bta.R;
import com.group_15.bta.objects.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentCoursesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentCoursesFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment student_courses.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentCoursesFragment newInstance(String param1, String param2) {
        StudentCoursesFragment fragment = new StudentCoursesFragment();
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