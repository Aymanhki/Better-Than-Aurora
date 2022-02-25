package com.group_15.bta;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.group_15.bta.R;
import com.group_15.bta.R.id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CourseLandingActivity extends AppCompatActivity{
    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayAdapter arrayAdapter;
    public String categoryName = "Default";
    int selectedPosition = -1;
    public Category selectedCat;
    SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_landing);

        searchView = (SearchView)findViewById(id.searchCategories);

        ActionBar actionBar = getSupportActionBar();//back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Category c = new Category("Computer Science");//This should be an array accessed in data
        categories.add(c);
        c = new Category("Engineering");
        categories.add(c);
        c = new Category("Education");
        categories.add(c);
        c = new Category("English");
        categories.add(c);
        c = new Category("French");
        categories.add(c);
        c = new Category("Kinesiology");
        categories.add(c);
        c = new Category("History");
        categories.add(c);
        c = new Category("Mathematics");
        categories.add(c);
        c = new Category("Slavic Studies");
        categories.add(c);
        c = new Category("Physics");
        categories.add(c);
        c = new Category("Yiddish");
        categories.add(c);

        Collections.sort(categories, new Comparator<Category>(){
            @Override
            public int compare(Category l, Category r) {
                int i = l.getName().compareTo(r.getName());
                return i;
            }
        });

        ListView listView = (ListView)findViewById(R.id.CategoriesList); //List of cats
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1,categories){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                text1.setText(categories.get(position).getName());

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CourseLandingActivity.this, CategoryActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("Category",categories.get(position).getCourses());
                b.putString("Title",categories.get(position).getName());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish(  );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void buttonAddCategory(View v){
        EditText category = (EditText) findViewById(id.newCategoryName);
        Category c = new Category(category.getText().toString());
        categories.add(c);
        Collections.sort(categories, new Comparator<Category>(){
            @Override
            public int compare(Category l, Category r) {
                int i = l.getName().compareTo(r.getName());
                return i;
            }
        });

        ListView listView = (ListView)findViewById(R.id.CategoriesList); //List of cats
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1,categories){
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
   /* public boolean onQueryTextSubmit(String query){
        for (int i =0; i<categories.size();i++) {
            if (categories.get(i).getName().contains(query)) {
                arrayAdapter.getFilter().;
            } else {
                Toast.makeText(CourseLandingActivity.this, "No Match Found", Toast.LENGTH_LONG);
            }
        }
        return false;
    }*/
}