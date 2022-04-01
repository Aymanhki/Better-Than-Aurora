package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.business.AccessStudents;

public class EditStudentActivity extends AppCompatActivity {
    private ArrayList<Student> students;
    private int position;

    private AccessStudents accessStudents;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        accessStudents = new AccessStudents();
        studentList = accessStudents.getStudentList();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // StudentPersistence studs = AccessStudents.getInstance();
      //  ArrayList<Student> students = studs.getStudentList();
        //students = (ArrayList<Student>) bundle.getSerializable("Students");
        position = bundle.getInt("Position");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
        editName.setText(studentList.get(position).getName());
        editPassword.setText(studentList.get(position).getPassword());

        final TextView tView = (TextView)findViewById(id.studentID);
        tView.setText(studentList.get(position).getPassword());

    }

    public void buttonEditStudent(View v) {
       // StudentPersistence studs = AccessStudents.getInstance();
        //studs.deleteStudent(studs.getStudentList().get(position));
        // students.remove(position);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
        Editable name = editName.getText();
        Editable password = editPassword.getText();
        final TextView tView = (TextView)findViewById(id.studentID);
        String id = (String) tView.getText();
        Student student = new Student(id, password.toString(), name.toString());
        // students.add(student);
        accessStudents = new AccessStudents();
        accessStudents.updateStudent(student);
        //studs.updateStudent(student);
        Intent createIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        //finish();
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("Students",students);
        //createIntent.putExtras(bundle);
        EditStudentActivity.this.startActivity(createIntent);
        //overridePendingTransition(0,0);
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
