package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;

/*
* Class for the admin menu
 */
public class AdminMenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //go to student side of admin
    public void buttonStudentsOnClick(View v) {
        Intent studentsIntent = new Intent(AdminMenuActivity.this, StudentListActivity.class);
        AdminMenuActivity.this.startActivity(studentsIntent);
    }

    //go to courses side of admin
    public void buttonCoursesOnClick(View v) {
        Intent courseIntent = new Intent(AdminMenuActivity.this, CourseLandingActivity.class);
        AdminMenuActivity.this.startActivity(courseIntent);
    }

    public void buttonLogOutOnClick(View v) {
        Intent courseIntent = new Intent(AdminMenuActivity.this, MainActivity.class);
        AdminMenuActivity.this.startActivity(courseIntent);
    }

    //back button
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
