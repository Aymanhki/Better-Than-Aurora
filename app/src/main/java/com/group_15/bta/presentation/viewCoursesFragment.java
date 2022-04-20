package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.CourseListAdapter;

import java.util.ArrayList;

public class viewCoursesFragment extends Fragment {


    private CourseListAdapter coursesAdapted;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ListView coursesList = view.findViewById(R.id.view_courses_course_list);
        ArrayList<Course> courses = new ArrayList<>();
        AccessCourses coursesGetter = new AccessCourses();
        for (String selectedCategory : selectedCategories) {
            courses.addAll(coursesGetter.getCategoryCourses(selectedCategory));
        }
        coursesAdapted = new CourseListAdapter(getContext(), R.layout.course_list_item, courses);
        coursesList.setAdapter(coursesAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        coursesList.setOnItemClickListener((adapterView, view1, i, l) -> navController.navigate(viewCoursesFragmentDirections.actionViewCoursesFragmentToViewSectionsFragment(courses.get(i))));
    }


}