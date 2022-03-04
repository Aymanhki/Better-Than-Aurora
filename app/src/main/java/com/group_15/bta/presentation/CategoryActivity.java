package com.group_15.bta.presentation;
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

import com.group_15.bta.objects.Courses;
import com.group_15.bta.R;
import com.group_15.bta.persistence.ICourseList;
import com.group_15.bta.persistence.CourseListData;


import java.util.ArrayList;

/*
* class for admin to add or delete a course
 */
public class CategoryActivity extends AppCompatActivity{

        protected String Name; //name of course category
        private ArrayList<Courses> courses; //list of courses
        protected ICourseList courseList = CourseListData.getInstance(); //course "database"

        public CategoryActivity()
        {
            courses = new ArrayList<Courses>();
        }

        ArrayAdapter arrayAdapter;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_category);

            //get name of the course category
            Bundle b = getIntent().getExtras();
            this.Name = b.getString("Title"); //should be some global call to get name

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            //let the textview contain the category name
            final TextView tView = (TextView)findViewById(R.id.CategoryName);
            tView.setText(this.Name);

            //get list of courses from "database" and list them
            courses = courseList.getCourseList();
            listCourses();

            //when course is clicked, go to course activity to edit that course
            ListView listView = (ListView) findViewById(R.id.coursesList);
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

        //add a course to the list
        public void buttonAddCourse(View v){
            //user input
            EditText CourseID = (EditText) findViewById(R.id.CourseID);
            EditText CourseName = (EditText) findViewById(R.id.CourseName);

            //create new course
            Courses c = new Courses(CourseID.getText().toString(),CourseName.getText().toString());
            courseList.insertCourses(c);
            courses = courseList.getCourseList();

            //show the new list of courses
            listCourses();
        }

    //delete a course from the list
    public void buttonDeleteCourse(View v){
        //get course to delete
        EditText CourseID = (EditText) findViewById(R.id.DeleteCourseID);

        //remove course from list
        for(int i =0; i< courses.size();i++){
            if(0 == courses.get(i).getID().compareTo(CourseID.getText().toString())){
                courseList.deleteCourses(i);
                courses = courseList.getCourseList();
            }
        }

        //show the new list of courses
        listCourses();
    }

    //lists the courses
    public void listCourses(){
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

    //back button
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
