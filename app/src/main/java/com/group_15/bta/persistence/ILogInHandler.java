package com.group_15.bta.persistence;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public interface ILogInHandler {
    public boolean validateLoginAttempt(String userName, String password);
    public Class intendedActivity(String userName, String password);
    public Intent destinationIntent(String userName, String password, Context currentActivity);
    public String getUserTypeString(String userName, String password);

}
