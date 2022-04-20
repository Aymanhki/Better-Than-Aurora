package com.group_15.bta.presentation;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.objects.Category;

import java.util.ArrayList;


public class viewCategories extends Fragment {


    private ArrayAdapter<String> categoriesNamesAdapted;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        ListView categoriesList =  view.findViewById(R.id.view_categories_list);
        ArrayList<Category> categories = new AccessCategories().getCategoryList();
        String[] categoriesNames = new String[categories.size()];
        ArrayList<String> toSend = new ArrayList<>();
        for(int i=0; i<categories.size(); i++)
        {
            categoriesNames[i] = categories.get(i).getName();
        }
        categoriesNamesAdapted = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, categoriesNames);
        categoriesList.setAdapter(categoriesNamesAdapted);
        categoriesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        NavController navController = NavHostFragment.findNavController(this);
        Button nextBtn =  view.findViewById(R.id.done_selecting_categories_btn);
        nextBtn.setOnClickListener(view1 -> {
            SparseBooleanArray checked = categoriesList.getCheckedItemPositions();

            for(int i=0; i<checked.size(); i++)
            {
                int position = checked.keyAt(i);
                if(checked.valueAt(i))
                {
                    toSend.add(categoriesNamesAdapted.getItem(position));
                }

            }
            String[] categoriesNames1 = toSend.toArray(new String[0]);
            nextBtn.setText(R.string.loading_text_for_any_btn);
            navController.navigate(viewCategoriesDirections.actionViewCategoriesToViewCoursesFragment(categoriesNames1));

        });
    }
}
