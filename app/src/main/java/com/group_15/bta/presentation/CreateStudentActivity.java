package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.objects.Student;
import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.presentation.Messages;
import com.group_15.bta.presentation.StudentListActivity;

public class CreateStudentActivity extends AppCompatActivity {
    //private ArrayList<Student> students;
    private AccessStudents accessStudents;

    StudentPersistence students = AccessStudents.getInstance();
    private ArrayList<Student> studentList = students.getStudentList();
    private ArrayAdapter<Student> studentArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bundle bundle =  getIntent().getExtras();
        // students = (ArrayList<Student>)bundle.getSerializable("Students");
        accessStudents = new AccessStudents();
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_admin_create_student);
    }

    public void buttonCreateStudent(View v) {
        EditText editID = (EditText)findViewById(R.id.newID);
        EditText editPassword = (EditText)findViewById(id.newPassword);
        EditText editName = (EditText)findViewById(R.id.newName);

        Student student = new Student(editID.getText().toString(), editPassword.getText().toString(), editName.getText().toString());
        //students.add(student);
        String result;

        result = validateStudentData(student);
        if (result == null) {
            try
            {
                accessStudents.insertStudent(student);
                studentList = accessStudents.getStudentList();
                Intent createIntent = new Intent(CreateStudentActivity.this, StudentListActivity.class);
                CreateStudentActivity.this.startActivity(createIntent);

            }
            catch(final Exception e)
            {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
        // StudentPersistence studs = AccessStudents.getInstance();
        //studs.insertStudent(student);
        // finish();
        // Bundle bundle = new Bundle();
        // bundle.putSerializable("Students",students);
        // createIntent.putExtras(bundle);
        // overridePendingTransition(0,0);

    }
    private boolean findStudent(Student student){
        boolean result = false;
        for (int i = 0; i < studentList.size(); i++){
            String curr = studentList.get(i).getStudentID();
            if (curr.equals(student.getStudentID())) {result = true;}
        }
        return result;
    }

    private String validateStudentData(Student student) {

        if (student.getID().length() == 0) {
            return "Student name required";
        }

        if (student.getStudentID().length() == 0) {
            return "Student ID required";
        }

        if (student.getStudentPassword().length() == 0) {
            return "Student password required";
        }

        if (findStudent(student)) {
            return "Student ID " + student.getStudentID() + " already exists.";
        }

        return null;
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