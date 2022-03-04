package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.objects.Student;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;
import com.group_15.bta.persistence.IStudentList;
import com.group_15.bta.persistence.StudentListData;
/*
 * class for admin to create a student
 */
public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_admin_create_student);
    }

    //creates a student from info provided in text views
    public void buttonCreateStudent(View v) {
        //get user input
        EditText editID = (EditText)findViewById(R.id.newID);
        EditText editPassword = (EditText)findViewById(id.newPassword);
        EditText editName = (EditText)findViewById(R.id.newName);

        //create new student
        Student student = new Student(editID.getText().toString(), editPassword.getText().toString(), editName.getText().toString());

        //add new student to "database"
        IStudentList studs = StudentListData.getInstance();
        studs.insertStudent(student);

        //go back to student list
        Intent createIntent = new Intent(CreateStudentActivity.this, StudentListActivity.class);
        CreateStudentActivity.this.startActivity(createIntent);

    }

}
