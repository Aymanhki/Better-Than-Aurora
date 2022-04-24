package com.group_15.bta.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.R;
import com.group_15.bta.application.Services;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.CourseListAdapter;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;


public class StudentHomeFragment extends Fragment {


    public StudentHomeFragment(){}

    private Student currentUser;
    private AccessStudents studentsPersistence;
    private NavController navController;
    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntries;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = (Student) new AccessUsers().getCurrentUser();
        studentsPersistence = new AccessStudents();
        pieEntries = studentsPersistence.getDegreeCreditBreakDown( currentUser );
        if(Services.getNewCourse()){
            Toast toast = Toast.makeText(getContext(), "New Courses Available", Toast.LENGTH_LONG);
            toast.show();
            Services.setCourseToFalse();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_student_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((StudentAccountActivity) requireActivity()).setActionBarTitle("Hi " + currentUser.getName());
        navController = NavHostFragment.findNavController(this);
        pieChart = view.findViewById(R.id.degree_breakdown_pie);
        setupPieChart();



    }

    public void setupPieChart() {
        PieDataSet pieDataSet = new PieDataSet(pieEntries, currentUser.getAssociatedDegree());
        int[] pieColors = new int[]{Color.rgb(58, 202, 116), Color.rgb(57, 153, 216), Color.rgb(234, 118, 110)};

        pieDataSet.setColors(pieColors);
        pieDataSet.setValueTextColor(Color.rgb(233, 229, 214));
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setValueTextSize(30f);
        pieChart.setData(new PieData( pieDataSet) );
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.animateX(300);
        pieChart.animateY(300);
        pieChart.getLegend().setEnabled(false);

    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        ArrayList<Section> studentCurrentSections = currentUser.getSections(true);
        SectionListAdapter enrolledSectionsAdapted = new SectionListAdapter(getContext(), R.layout.section_list_item, studentCurrentSections);
        ListView enrolledSectionsList = view.findViewById(R.id.student_enrolled_courses);
        enrolledSectionsList.setAdapter(enrolledSectionsAdapted);
        enrolledSectionsList.setOnItemClickListener((adapterView, view1, i, l) -> {
            StudentSection toSend = currentUser.getEnrolledSections().get(i);
            navController.navigate(StudentHomeFragmentDirections.actionStudentHomeToConfirmDroppingACourse(toSend));
        });
        TextView emptyEnrolledSectionsText = view.findViewById(R.id.student_enrolled_courses_empty);

        if (studentCurrentSections.isEmpty()) {
            enrolledSectionsList.setVisibility(View.GONE);
            emptyEnrolledSectionsText.setVisibility(View.VISIBLE);
        } else {
            enrolledSectionsList.setVisibility(View.VISIBLE);
            emptyEnrolledSectionsText.setVisibility(View.GONE);
        }

        ArrayList<Course> studentNeedToTakeCourses = studentsPersistence.getStudentDegreeNotTakenCourses(currentUser);
        CourseListAdapter needToTakeCoursesAdapted = new CourseListAdapter(getContext(), R.layout.course_list_item, studentNeedToTakeCourses);
        ListView needToTakeCourses = view.findViewById(R.id.student_required_courses);
        needToTakeCourses.setAdapter(needToTakeCoursesAdapted);
        needToTakeCourses.setOnItemClickListener((adapterView, view12, i, l) -> navController.navigate(StudentHomeFragmentDirections.actionStudentHomeToAddACourseWithCode(studentNeedToTakeCourses.get(i))));
        TextView needToTakeCourseEmptyText = view.findViewById(R.id.student_required_courses_empty);

        if (studentNeedToTakeCourses.isEmpty()) {
            needToTakeCourses.setVisibility(View.GONE);
            needToTakeCourseEmptyText.setVisibility(View.VISIBLE);
        } else {
            needToTakeCourses.setVisibility(View.VISIBLE);
            needToTakeCourseEmptyText.setVisibility(View.GONE);
        }


    }
}