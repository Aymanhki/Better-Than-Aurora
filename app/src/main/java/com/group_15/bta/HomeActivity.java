package com.group_15.bta;

import com.group_15.bta.R;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //copyDatabaseToDevice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonStudentsOnClick(View v) {
        Intent studentsIntent = new Intent(HomeActivity.this, StudentListActivity.class);
        HomeActivity.this.startActivity(studentsIntent);
    }
    public void buttonCoursesOnClick(View v) {
        Intent courseIntent = new Intent(HomeActivity.this, CourseLandingActivity.class);
        HomeActivity.this.startActivity(courseIntent);
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
