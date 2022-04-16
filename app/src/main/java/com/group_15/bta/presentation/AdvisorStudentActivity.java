package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.business.Calculate;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class AdvisorStudentActivity extends AppCompatActivity {
    private String studentID;
    private ArrayList<Student> currentStudent;
    private AccessStudents accessStudents;
    private ArrayList<Student> students = new ArrayList<>();

    private AccessStudentSections accessStudentSections;
    private ArrayList<StudentSection> studentSections = new ArrayList<>();

    private ArrayList<Section> currentSections = new ArrayList<>();
    private ArrayList<StudentSection> pastSections = new ArrayList<>();

    private ArrayAdapter<Section> sectionArrayAdapter;
    private ArrayAdapter<StudentSection> studentArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getString("ID");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_student);

        accessStudents = new AccessStudents();
        currentStudent = accessStudents.getStudent(new Student(studentID));
        displayStudentName(currentStudent.get(0));

        accessStudentSections = new AccessStudentSections();
        displayStudentGpa(currentStudent.get(0));

        currentSections = accessStudentSections.getSectionList(studentID,true);
        displayCurrentCourseList();

        pastSections = accessStudentSections.getStudentSectionList(studentID,false);
        displayPastCourseList();
    }

    private void displayStudentName(Student student){
        final TextView tView = (TextView)findViewById(R.id.StudentName);
        tView.setText( "Student: " + student.getName());
    }

    private void displayStudentGpa(Student student){
        final TextView tView = (TextView)findViewById(R.id.StudentGPA);
        String gpa = Calculate.gpa(accessStudentSections.getStudentSectionList(studentID));
        tView.setText( "GPA: " + gpa);
    }

    private void displayCurrentCourseList() {

        sectionArrayAdapter = new ArrayAdapter<Section>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, currentSections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(currentSections.get(position).getAssociatedCourse());
                text2.setText(currentSections.get(position).getSection());

                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.sectionsList);
        listView.setAdapter(sectionArrayAdapter);
    }

    private void displayPastCourseList() {

        studentArrayAdapter = new ArrayAdapter<StudentSection>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, pastSections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(pastSections.get(position).getAssociatedCourse().getTitle());
                text2.setText(pastSections.get(position).getGrade());

                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.pastSectionsList);
        listView.setAdapter(studentArrayAdapter);
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

