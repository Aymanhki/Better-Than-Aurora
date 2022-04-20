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
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.CourseListAdapter;


public class AddCourseCategoryFragment extends Fragment {


    private CourseListAdapter coursesNamesAdapted;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_course_from_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Category selectedCategory = AddCourseCategoryFragmentArgs.fromBundle(requireArguments()).getCategory();
        ListView coursesList = view.findViewById(R.id.courses_list_in_add_courses);
        coursesNamesAdapted = new CourseListAdapter(getContext(), R.layout.course_list_item, selectedCategory.getCourses());
        coursesList.setAdapter(coursesNamesAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        coursesList.setOnItemClickListener((adapterView, view1, i, l) -> navController.navigate(AddCourseCategoryFragmentDirections.actionAddACourseFromCategoryToAddACourseWithCode(selectedCategory.getCourses().get(i))));

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
                coursesNamesAdapted.getFilter().filter(newText);
                return false;
            }
        });
    }
}