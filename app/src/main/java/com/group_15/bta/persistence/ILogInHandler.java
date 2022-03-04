package com.group_15.bta.persistence;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public interface ILogInHandler {
     boolean validateLoginAttempt(String userName, String password);
     boolean validateLoginAttempt(User newUser);
     Class intendedActivity(String userName, String password);
     Intent destinationIntent(String userName, String password, Context currentActivity);
     String getUserTypeString(String userName, String password);
     String getUserTypeString(User newUser);
}
