package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group_15.bta.R;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessUsers;

public class AdvisorAccountActivity extends AppCompatActivity {

    private Advisor advisor;
    private Student advisorAccountInstance = new Student();
    private AccessUsers advisorLogInInstance = new AccessUsers(advisorAccountInstance);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        advisor = (Advisor) getIntent().getSerializableExtra(advisorLogInInstance.getUserTypeString(advisorAccountInstance));
        setContentView(R.layout.activity_advisor_account);
    }
}