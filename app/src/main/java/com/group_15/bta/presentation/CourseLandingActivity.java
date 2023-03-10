package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.objects.Category;

import java.util.ArrayList;
import java.util.Collections;

public class CourseLandingActivity extends AppCompatActivity {
    AccessCategories getter = new AccessCategories();
    private ArrayList<Category> categories = getter.getCategoryList();
    ArrayAdapter<String> arrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_landing);

        ActionBar actionBar = getSupportActionBar();//back button
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        listCategories();

        ListView listView = (ListView)findViewById(R.id.CategoriesList); //List of cats
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(CourseLandingActivity.this, CategoryActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("Category",categories.get(position).getCourses());
            b.putString("Title",categories.get(position).getName());
            i.putExtras(b);
            startActivity(i);
        });
    }

    public void buttonAddCategory(View v) {
        EditText category = (EditText) findViewById(id.newCategoryName);
        if(category.getText().toString().length() != 0){
            Category c = new Category(category.getText().toString());
            new AccessCategories().insertCategory(c);
        }
        else
        {
            Toast.makeText(CourseLandingActivity.this, "Please make sure all fields are filled.",Toast.LENGTH_LONG).show();
        }
        categories = new AccessCategories().getCategoryList();
        listCategories();
    }

    private void listCategories(){
        Collections.sort(categories, (l, r) -> l.getName().compareTo(r.getName()));

        ListView listView = (ListView)findViewById(R.id.CategoriesList); //List of cats
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, categories){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                text1.setText(categories.get(position).getName());

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
    }
    //back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}