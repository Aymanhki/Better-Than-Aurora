package com.group_15.bta.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group_15.bta.application.Main;
import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.business.DataGenerator;
import com.group_15.bta.objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private AccessUsers logInHandler;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button) findViewById(R.id.login);
        copyDatabaseToDevice();
        logInHandler = new AccessUsers();
        handleLogIn();
    }

    private void handleLogIn()
    {
        TextView username = (TextView) findViewById(R.id.userName);
        TextView password = (TextView) findViewById(R.id.password);



        View.OnClickListener loginAction = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                loginBtn.setText("Loading...");
                if (logInHandler.validateLoginAttempt(username.getText().toString(), password.getText().toString())) {
                    logInHandler.setCurrentUser(new User(username.getText().toString(), password.getText().toString()));
                    String successfulLoginMessage = "Log in Successful, Hi " + logInHandler.getCurrentUser().getName();
                    Toast.makeText(MainActivity.this, successfulLoginMessage, Toast.LENGTH_SHORT).show();
                    startActivity(logInHandler.destinationIntent(username.getText().toString(), password.getText().toString(), MainActivity.this));
                    username.setText("");
                    password.setText("");


                } else {
                    String failedLoginMessage = "Log in Failed, Sorry, user not found";
                    Toast.makeText(MainActivity.this, failedLoginMessage, Toast.LENGTH_SHORT).show();
                }

            }
        };

        loginBtn.setOnClickListener(loginAction);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginBtn.setText("Login");
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