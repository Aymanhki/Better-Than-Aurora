package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group_15.bta.R;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.LogInHandler;

public class InstructorAccount extends AppCompatActivity {

    private Instructor instructor;
    private Student instructorAccountInstance = new Student();
    private LogInHandler instructorLogInInstance = new LogInHandler(instructorAccountInstance);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instructor = (Instructor) getIntent().getSerializableExtra(instructorLogInInstance.getUserTypeString(instructorAccountInstance));
        setContentView(R.layout.activity_instructor_account);
    }
}