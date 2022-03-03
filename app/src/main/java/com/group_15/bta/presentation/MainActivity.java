package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group_15.bta.DataGenerator;
import com.group_15.bta.R;
import com.group_15.bta.advisor_account;
import com.group_15.bta.instructor_account;
import com.group_15.bta.persistence.User;
import com.group_15.bta.student_account;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataGenerator dataCreator = new DataGenerator();
    private ArrayList<User> users = dataCreator.createUsers();
    private final String STUDENT_ACCOUNT_TYPE = "Student";
    private final String ADVISOR_ACCOUNT_TYPE = "Advisor";
    private final String ADMINISTRATOR_ACCOUNT_TYPE = "Administrator";
    private final String INSTRUCTOR_ACCOUNT_TYPE = "Instructor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginHandler();
    }

    private void loginHandler()
    {
        TextView username = (TextView) findViewById(R.id.userName);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.login);

        View.OnClickListener loginAction = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                User loginAttempt = new User(username.getText().toString(), password.getText().toString());

                boolean found = false;
                for(int i=0; i<users.size(); i++)
                {
                    if(users.get(i).equals(loginAttempt) && !found)
                    {
                        loginAttempt = users.get(i);
                        found = true;
                    }
                }

                if(found)
                {
                    String[] userClasses = loginAttempt.getClass().toString().split("\\.");
                    System.out.println(userClasses.toString());
                    String userType = userClasses[userClasses.length-1];
                    System.out.println(userType);
                    String successfulLoginMessage = "Log in Successful, Hey "+userType;
                    Toast.makeText(MainActivity.this, successfulLoginMessage, Toast.LENGTH_SHORT).show();

                    Intent intent = null;

                    if(userType.equals(STUDENT_ACCOUNT_TYPE))
                    {
                        intent = new Intent(MainActivity.this, student_account.class);

                    }
                    else if(userType.equals(ADVISOR_ACCOUNT_TYPE))
                    {
                        intent = new Intent(MainActivity.this, advisor_account.class);
                    }
                    else if(userType.equals(ADMINISTRATOR_ACCOUNT_TYPE))
                    {
                        intent = new Intent(MainActivity.this, AdminMenuActivity.class);
                    }
                    else if(userType.equals(INSTRUCTOR_ACCOUNT_TYPE))
                    {
                        intent = new Intent(MainActivity.this, instructor_account.class);
                    }

                    startActivity(intent);

                }
                else
                {
                    String failedLoginMessage = "Log in Failed";
                    Toast.makeText(MainActivity.this, failedLoginMessage, Toast.LENGTH_SHORT).show();
                }
            }
        };

        loginBtn.setOnClickListener(loginAction);
    }


}