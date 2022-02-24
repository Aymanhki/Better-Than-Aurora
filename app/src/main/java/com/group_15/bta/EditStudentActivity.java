package com.group_15.bta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;
import com.group_15.bta.Student;

public class EditStudentActivity extends Activity{
    private ArrayList<Student> students;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        students = (ArrayList<Student>) bundle.getSerializable("Students");
        position = bundle.getInt("Position");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);
        EditText editID = (EditText) findViewById(R.id.editStudentID);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
            editID.setText(students.get(position).getStudentID());
            editName.setText(students.get(position).getStudentName());
            editPassword.setText(students.get(position).getStudentPassword());


    }

    public void buttonEditStudent(View v) {
        students.remove(position);
        EditText editID = (EditText) findViewById(R.id.editStudentID);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
        Editable id = editID.getText();
        Editable name = editName.getText();
        Editable password = editPassword.getText();
        Student student = new Student(id.toString(), password.toString(), name.toString());
        students.add(student);
        Intent createIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        finish();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Students",students);
        createIntent.putExtras(bundle);
        EditStudentActivity.this.startActivity(createIntent);
        overridePendingTransition(0,0);
    }

    public void buttonBackOnClick(View v) {
        Intent backIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        EditStudentActivity.this.startActivity(backIntent);
    }
}
