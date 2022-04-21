package com.group_15.bta.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.application.Main;
import com.group_15.bta.business.AccessUsers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {



    private AccessUsers logInHandler;
    private Button loginBtn;
    private TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn =  findViewById(R.id.login);
        forgetPassword = findViewById(R.id.forgetPassword);
        copyDatabaseToDevice();
        logInHandler = new AccessUsers();
        handleLogIn();


    }

    private void handleLogIn()
    {
        TextView username = findViewById(R.id.userName);
        TextView password = findViewById(R.id.password);

        View.OnClickListener loginAction = view -> {
            loginBtn.setText(R.string.loading_text_for_any_btn);
            String userNameString = username.getText().toString();
            String passwordString = password.getText().toString();
            if(userNameString.length() > 0 && passwordString.length() > 0)
            {
                if (logInHandler.validateLoginAttempt(userNameString, passwordString)) {
                    logInHandler.setCurrentUser(userNameString, passwordString);
                    String successfulLoginMessage = "Log in Successful, Hi " + logInHandler.getCurrentUser().getName();
                    Toast.makeText(MainActivity.this, successfulLoginMessage, Toast.LENGTH_SHORT).show();
                    startActivity(logInHandler.destinationIntent(username.getText().toString(), password.getText().toString(), MainActivity.this));
                    username.setText("");
                    password.setText("");
                } else {
                    String failedLoginMessage = "Log in Failed, Sorry, user not found";
                    loginBtn.setText(R.string.login_button_text_in_main_activity);
                    Toast.makeText(MainActivity.this, failedLoginMessage, Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                loginBtn.setText(R.string.login_button_text_in_main_activity);
            }
        };

        loginBtn.setOnClickListener(loginAction);

        forgetPassword.setOnClickListener(view -> Messages.message(MainActivity.this, "Please Contact IST Service Desk\nInformation Services and Technology\n" +
                "123 Fletcher Argue\n" +
                "University of Manitoba, Winnipeg, MB R3T 2N2 Canada\n" +
                "Office: 204-474-8600   Fax: 204-474-7983\n" +
                "Servicedesk@umanitoba.ca"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginBtn.setText(R.string.login_button_text_in_main_activity);
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


}