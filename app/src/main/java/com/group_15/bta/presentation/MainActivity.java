package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group_15.bta.business.DataGenerator;
import com.group_15.bta.R;
import com.group_15.bta.business.LogInHandler;

public class MainActivity extends AppCompatActivity {



    private LogInHandler logInHandler = new LogInHandler(new DataGenerator().createUsers());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleLogIn();
    }

    private void handleLogIn()
    {
        TextView username = (TextView) findViewById(R.id.userName);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.login);


        View.OnClickListener loginAction = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(logInHandler.validateLoginAttempt(username.getText().toString(), password.getText().toString()))
                {
                    String successfulLoginMessage = "Log in Successful, Hi "+username.getText().toString();
                    Toast.makeText(MainActivity.this, successfulLoginMessage, Toast.LENGTH_SHORT).show();
                    startActivity(logInHandler.destinationIntent(username.getText().toString(), password.getText().toString(), MainActivity.this));
                    username.setText("");
                    password.setText("");
                }
                else
                {
                    String failedLoginMessage = "Log in Failed, Sorry, user not found";
                    Toast.makeText(MainActivity.this, failedLoginMessage, Toast.LENGTH_SHORT).show();
                }
            }
        };

        loginBtn.setOnClickListener(loginAction);
    }



}