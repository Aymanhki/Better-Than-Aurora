package com.group_15.bta.persistence;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.objects.User;

import java.util.ArrayList;

public interface UserPersistence {
    ArrayList<User> getUsers();

    void insertUser(User newUser);

    void deleteUser(User toRemove);

    User getCurrentUser();

    void setCurrentUser(String userName, String password);

    boolean validateLoginAttempt(String userName, String password);


    Class intendedActivity(String userName, String password);

    Intent destinationIntent(String userName, String password, Context currentActivity);

    User getUser(String userName, String password);
}
