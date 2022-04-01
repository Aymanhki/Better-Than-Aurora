package com.group_15.bta.presentation;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    ArrayList<StudentSection> studentSections;
    String gpa;
    String creditHours;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment history.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        studentSections = new AccessStudentSections().getStudentSectionList(new AccessUsers().getCurrentUser().getID(), false);
        gpa = Calculate.gpa(studentSections);
        creditHours = Calculate.creditHours(studentSections);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView gpaBox = (TextView) view.findViewById(R.id.gpa_history_text);
        TextView creditHoursBox = (TextView) view.findViewById(R.id.credit_hours_history_text);
        ListView completedCourses = (ListView) view.findViewById(R.id.completed_courses_student_section_list);
        TextView noCourses = (TextView) view.findViewById(R.id.completed_courses_student_section_list_empty_text);
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