package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.objects.Student;
import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;
import com.group_15.bta.persistence.StudentList;
import com.group_15.bta.business.AccessStudents;

public class CreateStudentActivity extends AppCompatActivity {
private ArrayList<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bundle bundle =  getIntent().getExtras();
       // students = (ArrayList<Student>)bundle.getSerializable("Students");

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_admin_create_student);
    }

    public void buttonCreateStudent(View v) {
        EditText editID = (EditText)findViewById(R.id.newID);
        EditText editPassword = (EditText)findViewById(id.newPassword);
        EditText editName = (EditText)findViewById(R.id.newName);

        Student student = new Student(editID.getText().toString(), editPassword.getText().toString(), editName.getText().toString());
        //students.add(student);
        StudentList studs = AccessStudents.getInstance();
        studs.insertStudent(student);
        Intent createIntent = new Intent(CreateStudentActivity.this, StudentListActivity.class);
       // finish();
       // Bundle bundle = new Bundle();
       // bundle.putSerializable("Students",students);
       // createIntent.putExtras(bundle);
        CreateStudentActivity.this.startActivity(createIntent);
       // overridePendingTransition(0,0);

    }

    public void buttonBackOnClick(View v) {
        Intent backIntent = new Intent(CreateStudentActivity.this, StudentListActivity.class);
        CreateStudentActivity.this.startActivity(backIntent);
    }


}
