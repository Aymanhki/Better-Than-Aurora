package com.group_15.bta.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    private AccessStudentSections accessStudentSections;

    private ArrayList<Section> currentSections = new ArrayList<>();
    private ArrayList<StudentSection> pastSections = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getString("ID");

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_student);

        AccessStudents accessStudents = new AccessStudents();
        ArrayList<Student> currentStudent = accessStudents.getStudent(new Student(studentID));
        displayStudentName(currentStudent.get(0));

        accessStudentSections = new AccessStudentSections();
        displayStudentGpa();

        currentSections = accessStudentSections.getSectionList(studentID,true);
        displayCurrentCourseList();

        pastSections = accessStudentSections.getStudentSectionList(studentID,false);
        displayPastCourseList();
    }

    @SuppressLint("SetTextI18n")
    private void displayStudentName(Student student){
        final TextView tView = findViewById(R.id.StudentName);
        tView.setText( "Student: " + student.getName());
    }

    @SuppressLint("SetTextI18n")
    private void displayStudentGpa(){
        final TextView tView = findViewById(R.id.StudentGPA);
        String gpa = Calculate.gpa(accessStudentSections.getStudentSectionList(studentID)).toString();
        tView.setText("GPA: " + gpa);
    }

    private void displayCurrentCourseList() {

        ArrayAdapter<Section> sectionArrayAdapter = new ArrayAdapter<Section>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, currentSections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(currentSections.get(position).getAssociatedCourse());
                text2.setText(currentSections.get(position).getSection());

                return view;
            }
        };

        ListView listView = findViewById(R.id.sectionsList);
        listView.setAdapter(sectionArrayAdapter);

    }

    private void displayPastCourseList() {

        ArrayAdapter<StudentSection> studentArrayAdapter = new ArrayAdapter<StudentSection>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, pastSections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(pastSections.get(position).getAssociatedCourse().getTitle());
                text2.setText(pastSections.get(position).getGrade().toString());

                return view;
            }
        };

        ListView listView = findViewById(R.id.pastSectionsList);
        listView.setAdapter(studentArrayAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

