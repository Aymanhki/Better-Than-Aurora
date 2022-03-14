package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group_15.bta.R;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessUsers;

public class InstructorAccount extends AppCompatActivity {

    private Instructor instructor;
    private Student instructorAccountInstance = new Student();
    private AccessUsers instructorLogInInstance = new AccessUsers(instructorAccountInstance);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instructor = (Instructor) getIntent().getSerializableExtra(instructorLogInInstance.getUserTypeString(instructorAccountInstance));
        setContentView(R.layout.activity_instructor_account);
    }
}