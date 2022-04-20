package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;

public class InstructorMenuActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_menu);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonInstructorSectionsOnClick(View v) {
        Intent studentsIntent = new Intent(InstructorMenuActivity.this, InstructorSectionsActivity.class);
        InstructorMenuActivity.this.startActivity(studentsIntent);
    }

    public void buttonLogOutOnClick(View v) {
        Intent courseIntent = new Intent(InstructorMenuActivity.this, MainActivity.class);
        InstructorMenuActivity.this.startActivity(courseIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}