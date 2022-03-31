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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.objects.Course;
import com.group_15.bta.R;
import com.group_15.bta.business.AccessCourses;


import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    protected String Name;
    private ArrayList<Course> courses;
    protected AccessCourses courseList = new AccessCourses();

    public CategoryActivity() {
        courses = new ArrayList<Course>();
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

            courses = courseList.getCategoryCourses(Name);

            listCourses();

            ListView listView = (ListView) findViewById(R.id.coursesList);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle b = new Bundle();
                    Intent i = new Intent(CategoryActivity.this, CourseActivity.class);
                    b.putString("Title",courses.get(position).getID());
                    b.putString("Description",courses.get(position).getDescription());
                    b.putString("Category", Name);
                    i.putExtras(b);
                    startActivity(i);
                }
            });
        }


        public void buttonAddCourse(View v){
            EditText CourseID = (EditText) findViewById(R.id.CourseID);
            EditText CourseName = (EditText) findViewById(R.id.CourseName);
            EditText CourseDescription = (EditText) findViewById(R.id.CourseDescription);
            EditText CourseCreditHours = (EditText) findViewById(R.id.CourseCreditHours);
            EditText TuitionFee = (EditText) findViewById(R.id.TuitionFee);

            if(CourseID.getText().toString().length() != 0 && CourseName.getText().toString().length() != 0 &&
                    CourseDescription.getText().toString().length() != 0 && CourseCreditHours.getText().toString().length() != 0
            && TuitionFee.getText().toString().length() !=0) {

                double TF = Double.parseDouble(TuitionFee.getText().toString());
                int CH =  Integer.parseInt(CourseCreditHours.getText().toString());
                Course c = new Course(CourseID.getText().toString(), CourseName.getText().toString(), CourseDescription.getText().toString(), CH, Name, TF);
                courseList.insertCourses(c);
            }
            else
            {
                Toast.makeText(CategoryActivity.this, "Please make sure all fields are filled.",Toast.LENGTH_LONG).show();
            }
            courses = courseList.getCategoryCourses(Name);

            listCourses();
        }

    public void buttonDeleteCourse(View v){
        EditText CourseID = (EditText) findViewById(R.id.DeleteCourseID);

        for(int i =0; i< courses.size();i++){
            if(0 == courses.get(i).getID().compareTo(CourseID.getText().toString())){
                courseList.deleteCourses(courses.get(i));
                courses = courseList.getCategoryCourses(Name);
            }
        }
        listCourses();
    }

    public void listCourses(){
        ListView listView = (ListView) findViewById(R.id.coursesList);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courses) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(courses.get(position).getID() + " " + courses.get(position).getTitle());
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
