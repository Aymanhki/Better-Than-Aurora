package com.group_15.bta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.Student;
import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;

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
        StudentList studs = com.group_15.bta.StudentListData.getInstance();
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
