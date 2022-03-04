package com.group_15.bta.presentation;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.Data;
import com.group_15.bta.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {

    private ListView categoriesList;
    private ArrayList<Category> categories;
    private String[] categoriesName;
    private ArrayAdapter<String> categoriesNamesAdapted;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_a_course.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
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

        categories = Data.getBTACategories(getContext());

        categoriesName = new String[categories.size()];
        categoriesList = view.findViewById(R.id.categories_list_in_add_courses_fragment);


        //Fake data test:
        ArrayList<Courses> courses = new ArrayList<>();
        ArrayList<Section> sections = new ArrayList<>();

        //String section, String[] days, String[] time, String instructor, String location, int waitListCap, int available, int CAP
        sections.add(new Section("COMP 3350 - A01", new String[]{"Monday", "Wednesday", "Friday"}, new String[]{"9:30 AM - 10:20 PM", "9:30 AM - 10:20 PM", "9:30 AM - 10:20 PM"}, "Dr. Heather Matheson", "Remote", 0, 0, 0));
        courses.add(new Courses("COMP 3350", "Software Engineering 1", "<Description goes here>", sections));
        categories.add(new Category("Computer Science", courses));
        categoriesName = new String[categories.size()];
        for(int i=0; i<categories.size(); i++)
        {
            categoriesName[i]=categories.get(i).getName();
        }

        categoriesNamesAdapted = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, categoriesName);
        categoriesList.setAdapter(categoriesNamesAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navController.navigate(AddCourseFragmentDirections.actionAddACourseToAddACourseFromCategory(categories.get(i)));
            }
        });
    }
}