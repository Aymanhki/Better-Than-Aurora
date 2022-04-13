package com.group_15.bta.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Student currentUser = (Student) new AccessUsers().getCurrentUser();
    ;
    private ListView enrolledSectionsList;
    private ListView needToTakeCourses;
    private TextView needToTakeCourseEmptyText;
    private TextView emptyEnrolledSectionsText;
    private SectionListAdapter enrolledSectionsAdapted;
    private CourseListAdapter needToTakeCoursesAdapted;
    private AccessStudents studentsPersistence = new AccessStudents();

    private NavController navController;

    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntries = studentsPersistence.getDegreeCreditBreakDown( currentUser );
    public StudentHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment student_home.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHomeFragment newInstance(String param1, String param2) {
        StudentHomeFragment fragment = new StudentHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StudentAccountActivity) getActivity()).setActionBarTitle("Hi " + currentUser.getName());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        ((StudentAccountActivity) getActivity()).setActionBarTitle("Hi " + currentUser.getName());
        navController = NavHostFragment.findNavController(this);
        pieChart = view.findViewById(R.id.degree_breakdown_pie);
        setupPieChart();

        ArrayList<Section> studentCurrentSections = currentUser.getSections(true);
        enrolledSectionsAdapted = new SectionListAdapter(getContext(), R.layout.section_list_item, studentCurrentSections);
        enrolledSectionsList = view.findViewById(R.id.student_enrolled_courses);
        enrolledSectionsList.setAdapter(enrolledSectionsAdapted);
        enrolledSectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StudentSection toSend = currentUser.getEnrolledSections().get(i);
                navController.navigate(StudentHomeFragmentDirections.actionStudentHomeToConfirmDroppingACourse(toSend));
            }
        });
        emptyEnrolledSectionsText = view.findViewById(R.id.student_enrolled_courses_empty);

        if (studentCurrentSections.isEmpty()) {
            enrolledSectionsList.setVisibility(View.GONE);
            emptyEnrolledSectionsText.setVisibility(View.VISIBLE);
        } else {
            enrolledSectionsList.setVisibility(View.VISIBLE);
            emptyEnrolledSectionsText.setVisibility(View.GONE);
        }

        ArrayList<Course> studentNeedToTakeCourses = studentsPersistence.getStudentDegreeNotTakenCourses(currentUser);
        needToTakeCoursesAdapted = new CourseListAdapter(getContext(), R.layout.course_list_item, studentNeedToTakeCourses);
        needToTakeCourses = view.findViewById(R.id.student_required_courses);
        needToTakeCourses.setAdapter(needToTakeCoursesAdapted);
        needToTakeCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navController.navigate(StudentHomeFragmentDirections.actionStudentHomeToAddACourseWithCode(studentNeedToTakeCourses.get(i)));
            }
        });
        needToTakeCourseEmptyText = view.findViewById(R.id.student_required_courses_empty);

        if (studentNeedToTakeCourses.isEmpty()) {
            needToTakeCourses.setVisibility(View.GONE);
            needToTakeCourseEmptyText.setVisibility(View.VISIBLE);
        } else {
            needToTakeCourses.setVisibility(View.VISIBLE);
            needToTakeCourseEmptyText.setVisibility(View.GONE);
        }


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

}