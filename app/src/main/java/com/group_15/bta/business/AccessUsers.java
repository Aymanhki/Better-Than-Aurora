package com.group_15.bta.business;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;

import java.util.ArrayList;

public class AccessUsers implements UserPersistence {

    private UserPersistence userPersistence;


    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
    }


    public AccessUsers(final UserPersistence userPersistence) {
        this();
        this.userPersistence = userPersistence;
    }


    @Override
    public Intent destinationIntent(String userName, String password, Context currentActivity) {
        return userPersistence.destinationIntent(userName, password, currentActivity);
    }

    @Override
    public Class intendedActivity(String userName, String password) {
        return userPersistence.intendedActivity(userName, password);
    }

    @Override
    public boolean validateLoginAttempt(String userName, String password) {
        return userPersistence.validateLoginAttempt(userName, password);
    }

    @Override
    public ArrayList<User> getUsers() {
        return userPersistence.getUsers();
    }

    @Override
    public void insertUser(User newUser) {
        userPersistence.insertUser(newUser);
    }

    @Override
    public void deleteUser(User toRemove) {
        userPersistence.deleteUser(toRemove);
    }

    @Override
    public User getCurrentUser() {
        return userPersistence.getCurrentUser();
    }

    @Override
    public void setCurrentUser(String userName, String password) {
        userPersistence.setCurrentUser(userName, password);
    }

    @Override
    public User getUser(String userName, String password) {
          return userPersistence.getUser(userName, password);
    }

}
