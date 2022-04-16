package com.group_15.bta.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessUsers;

import java.util.ArrayList;

public class AdvisorMenuActivity extends AppCompatActivity {

    private Advisor advisor;
    private Student advisorAccountInstance = new Student();
    private AccessUsers advisorLogInInstance = new AccessUsers(advisorAccountInstance);
    private AccessStudents accessStudents;
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        advisor = (Advisor) getIntent().getSerializableExtra(advisorLogInInstance.getUserTypeString(advisorAccountInstance));
        setContentView(R.layout.activity_advisor_menu);
        accessStudents = new AccessStudents();
        students = accessStudents.getStudentList();

    }


    public void buttonAdvisorSearchStudent(View v) {
        EditText StudentID = (EditText) findViewById(R.id.AdvisorStudentID);

        String result;

        result = validateStudentData(StudentID.getText().toString());
        if (result == null) {
            try {
                Intent editIntent = new Intent(AdvisorMenuActivity.this, AdvisorStudentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", StudentID.getText().toString());
                editIntent.putExtras(bundle);
                AdvisorMenuActivity.this.startActivity(editIntent);
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
        StudentID.setText(null);
    }

    public String validateStudentData(String id) {
        ArrayList<Student> student = accessStudents.getStudent(new Student(id));
        if (student.size() == 0) {
            return "Student id: " + id + " not found.";
        }
        return null;
    }

    public void buttonLogOutOnClick(View v) {
        Intent courseIntent = new Intent(AdvisorMenuActivity.this, MainActivity.class);
        AdvisorMenuActivity.this.startActivity(courseIntent);
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