package com.group_15.bta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.User;
import com.group_15.bta.presentation.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewCategories extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayAdapter categoriesNamesAdapted;
    public viewCategories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewCategories.
     */
    // TODO: Rename and change types and number of parameters
    public static viewCategories newInstance(String param1, String param2) {
        viewCategories fragment = new viewCategories();
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
        return inflater.inflate(R.layout.fragment_view_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView categoriesList = (ListView) view.findViewById(R.id.view_categories_list);
        ArrayList<Category> categories = new AccessCategories().getCategoryList();
        String[] categoriesNames = new String[categories.size()];
        ArrayList<String> toSend = new ArrayList<>();
        for(int i=0; i<categories.size(); i++)
        {
            categoriesNames[i] = categories.get(i).getName();
        }
        categoriesNamesAdapted = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, categoriesNames);
        categoriesList.setAdapter(categoriesNamesAdapted);
        categoriesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        NavController navController = NavHostFragment.findNavController(this);
        Button nextBtn = (Button) view.findViewById(R.id.done_selecting_categories_btn);
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = categoriesList.getCheckedItemPositions();

                for(int i=0; i<checked.size(); i++)
                {
                    int position = checked.keyAt(i);
                    if(checked.valueAt(i))
                    {
                        toSend.add((String) categoriesNamesAdapted.getItem(position));
                    }

                }
                String[] categoriesNames  = toSend.toArray(new String[0]);
                nextBtn.setText("Loading...");
                navController.navigate(viewCategoriesDirections.actionViewCategoriesToViewCoursesFragment(categoriesNames));

            }
        });
    }
}
