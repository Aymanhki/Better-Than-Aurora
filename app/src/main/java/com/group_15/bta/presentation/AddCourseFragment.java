package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.objects.Category;

import java.util.ArrayList;


public class AddCourseFragment extends Fragment {

    private ArrayList<Category> categories;
    private ArrayAdapter<String> categoriesNamesAdapted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_course, container, false);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.courses_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.categories_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Categories");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                categoriesNamesAdapted.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categories = new AccessCategories().getCategoryList();
        String[] categoriesName = new String[categories.size()];
        ListView categoriesList = view.findViewById(R.id.categories_list_in_add_courses_fragment);
        for(int i=0; i<categories.size(); i++)
        {
            categoriesName[i]=categories.get(i).getName();
        }

        categoriesNamesAdapted = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, categoriesName);
        categoriesList.setAdapter(categoriesNamesAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        categoriesList.setOnItemClickListener((adapterView, view1, i, l) -> navController.navigate(AddCourseFragmentDirections.actionAddACourseToAddACourseFromCategory(categories.get(i))));
    }
}