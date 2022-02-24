package com.group_15.bta;

import com.group_15.bta.R;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
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

}
