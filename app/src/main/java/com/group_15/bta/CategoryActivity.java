package com.group_15.bta;
//goes with activity_courses
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;


import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity{
        protected String Name;
        private ArrayList<Courses> courses;

        public CategoryActivity()
        {
            courses = new ArrayList<Courses>();
        }

        ArrayAdapter arrayAdapter;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_category);

            Bundle b = getIntent().getExtras();
            this.Name = b.getString("Title"); //should be some global call to get name

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);


            final TextView tView = (TextView)findViewById(R.id.CategoryName);
            tView.setText(this.Name);

            CourseList courseList = com.group_15.bta.CourseListData.getInstance();
            courses = courseList.getCourseList();
/*
            Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
            courses.add(c);
            c = new Courses("Comp 1020", "Introduction to Computer Science 2");
            courses.add(c); */

            ListView listView = (ListView) findViewById(R.id.coursesList);
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courses) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(courses.get(position).getID());
                    text2.setText(courses.get(position).getDescription());

                    return view;
                }
            };
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle b = new Bundle();
                    Intent i = new Intent(CategoryActivity.this, CourseActivity.class);
                    b.putString("Title",courses.get(position).getID());
                    b.putString("Description",courses.get(position).getDescription());
                    i.putExtras(b);
                    startActivity(i);
                }
            });
        }


        public void buttonAddCourse(View v){
            EditText CourseID = (EditText) findViewById(R.id.CourseID);
            EditText CourseName = (EditText) findViewById(R.id.CourseName);

            Courses c = new Courses(CourseID.getText().toString(),CourseName.getText().toString());

            courses.add(c);

            ListView listView = (ListView) findViewById(R.id.coursesList);
             arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courses) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(courses.get(position).getID());
                    text2.setText(courses.get(position).getDescription());

                    return view;
                }
            };
             listView.setAdapter(arrayAdapter);
        }

    public void buttonDeleteCourse(View v){
        EditText CourseID = (EditText) findViewById(R.id.DeleteCourseID);

        for(int i =0; i< courses.size();i++){
            if(0 == courses.get(i).getID().compareTo(CourseID.getText().toString())){
                courses.remove(i);
            }
        }

        ListView listView = (ListView) findViewById(R.id.coursesList);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courses) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(courses.get(position).getID());
                text2.setText(courses.get(position).getDescription());

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
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
}
