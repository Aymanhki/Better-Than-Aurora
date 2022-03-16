package com.group_15.bta.persistence;
import android.content.Context;
import android.content.Intent;

import com.group_15.bta.objects.User;

public interface ILogInHandler {
    boolean validateLoginAttempt(String userName, String password);

    boolean validateLoginAttempt(User newUser);

    Class intendedActivity(String userName, String password);

    Intent destinationIntent(String userName, String password, Context currentActivity);

    String getUserTypeString(String userName, String password);

    String getUserTypeString(User newUser);

    User getCurrentUser();

    void setCurrentUser(User newUser);
}
