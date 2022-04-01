package com.group_15.bta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.CourseListAdapter;
import com.group_15.bta.presentation.AddCourseCategoryFragmentArgs;
import com.group_15.bta.presentation.AddCourseCodeFragmentDirections;
import com.group_15.bta.presentation.AddCourseFragmentDirections;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewCoursesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CourseListAdapter coursesAdapted;
    public viewCoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewCoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static viewCoursesFragment newInstance(String param1, String param2) {
        viewCoursesFragment fragment = new viewCoursesFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.courses_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.categories_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Courses");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                coursesAdapted.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] selectedCategories = viewCoursesFragmentArgs.fromBundle(requireArguments()).getCategories();
        ListView coursesList = (ListView) view.findViewById(R.id.view_courses_course_list);
        ArrayList<Course> courses = new ArrayList<>();
        AccessCourses coursesGetter = new AccessCourses();
        for(int i=0; i<selectedCategories.length; i++)
        {
            courses.addAll(coursesGetter.getCategoryCourses(selectedCategories[i]));
        }
        coursesAdapted = new CourseListAdapter(getContext(), R.layout.course_list_item, courses);
        coursesList.setAdapter(coursesAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navController.navigate(viewCoursesFragmentDirections.actionViewCoursesFragmentToViewSectionsFragment(courses.get(i)));
            }
        });
    }


}