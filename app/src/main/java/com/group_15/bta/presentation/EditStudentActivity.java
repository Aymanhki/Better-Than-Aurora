package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.IStudentList;
import com.group_15.bta.persistence.StudentListData;
/*
 * class for admin to edit a student
 */
public class EditStudentActivity extends AppCompatActivity {

    private ArrayList<Student> students;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        IStudentList studs = StudentListData.getInstance(); //access "database"

        //get list of students and position of student to edit
        students = studs.getStudentList();
        position = bundle.getInt("Position");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);

        //fill textviews with current student info
        EditText editID = (EditText) findViewById(R.id.editStudentID);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);

            editID.setText(students.get(position).getStudentID());
            editName.setText(students.get(position).getStudentName());
            editPassword.setText(students.get(position).getStudentPassword());


    }

    //edit student based on input and return to student list
    public void buttonEditStudent(View v) {
        IStudentList studs = StudentListData.getInstance();

        //delete student we are editing
        studs.deleteStudent(position);

        //get edited student info
        EditText editID = (EditText) findViewById(R.id.editStudentID);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);

        Editable id = editID.getText();
        Editable name = editName.getText();
        Editable password = editPassword.getText();

        //create a new student and add to list
        Student student = new Student(id.toString(), password.toString(), name.toString());
        studs.insertStudent(student);

        //back to student list
        Intent createIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        EditStudentActivity.this.startActivity(createIntent);
    }

}
