package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessUsers;

public class AdminMenuActivity extends AppCompatActivity{

    private Administrator admin;
    private Student adminAccountInstance = new Student();
    private AccessUsers adminLogInInstance = new AccessUsers(adminAccountInstance);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        admin = (Administrator) getIntent().getSerializableExtra(adminLogInInstance.getUserTypeString(adminAccountInstance));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //copyDatabaseToDevice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonStudentsOnClick(View v) {
        Intent studentsIntent = new Intent(AdminMenuActivity.this, StudentListActivity.class);
        AdminMenuActivity.this.startActivity(studentsIntent);
    }
    public void buttonCoursesOnClick(View v) {
        Intent courseIntent = new Intent(AdminMenuActivity.this, CourseLandingActivity.class);
        AdminMenuActivity.this.startActivity(courseIntent);
    }

    public void buttonLogOutOnClick(View v) {
        Intent courseIntent = new Intent(AdminMenuActivity.this, MainActivity.class);
        AdminMenuActivity.this.startActivity(courseIntent);
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
