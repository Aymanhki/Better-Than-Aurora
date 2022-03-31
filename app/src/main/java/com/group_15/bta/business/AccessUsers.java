package com.group_15.bta.business;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.ILogInHandler;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.presentation.AdminMenuActivity;
import com.group_15.bta.presentation.AdvisorAccountActivity;
import com.group_15.bta.presentation.InstructorAccount;
import com.group_15.bta.presentation.StudentAccountActivity;
import com.group_15.bta.presentation.InstructorSectionsActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class AccessUsers implements ILogInHandler, UserPersistence {

    private UserPersistence userPersistence = Services.getUserPersistence();
    private final ArrayList<User> appCurrentUsers;
    private User currentUser = null;
    public final String INVALID_DATA_MESSAGE = "Multiple Users";

    public AccessUsers() {
        appCurrentUsers = userPersistence.getUsers();
    }

    public AccessUsers(User newUser) {
        appCurrentUsers = new ArrayList<>();
        appCurrentUsers.add(newUser);
    }

    public AccessUsers(ArrayList<User> newAppUsers) {
        if (validateDataBase(newAppUsers)) {
            appCurrentUsers = newAppUsers;
        } else {
            appCurrentUsers = new ArrayList<>();
        }
    }

    public AccessUsers(final UserPersistence userPersistence) {
        this();
        this.userPersistence = userPersistence;
    }

    @Override
    public Class intendedActivity(String userName, String password) {
        Class toReturn = null;

        if(validateLoginAttempt(userName, password))
        {
            User loginAttempt = getUser(userName, password);

            if(loginAttempt instanceof Student)
            {
                toReturn = StudentAccountActivity.class;
            }
            else if(loginAttempt instanceof Administrator)
            {
                toReturn = AdminMenuActivity.class;
            }
            else if(loginAttempt instanceof Advisor)
            {
                toReturn = AdvisorAccountActivity.class;
            }
            else if(loginAttempt instanceof Instructor)
            {
                toReturn = InstructorSectionsActivity.class;
            }
        }

        return toReturn;
    }

    @Override
    public Intent destinationIntent(String userName, String password, Context currentActivity) {
        Intent toReturn = new Intent(currentActivity, currentActivity.getClass());

        if(validateLoginAttempt(userName, password))
        {
            toReturn = new Intent(currentActivity, intendedActivity(userName, password))
            .putExtra(getUserTypeString(userName, password), (Serializable) getUser(userName, password));
        }

        return toReturn;
    }

    @Override
    public boolean validateLoginAttempt(String userName, String password)
    {
        boolean userFound = false;

        for(int i=0; i<appCurrentUsers.size() && !userFound; i++)
        {
            if(appCurrentUsers.get(i).equals(new User(userName, password)))
            {
                userFound = true;
            }
        }

        return userFound;
    }

    @Override
    public boolean validateLoginAttempt(User newUser)
    {
        boolean userFound = false;

        for(int i=0; i<appCurrentUsers.size() && !userFound; i++)
        {
            if(appCurrentUsers.get(i).equals(newUser))
            {
                userFound = true;
            }
        }

        return userFound;
    }

    @Override
    public String getUserTypeString(String userName, String password)
    {
        String toReturn = "User Not Found";
        if(validateLoginAttempt(userName, password))
        {
            String[] userClasses = getUser(userName,password).getClass().toString().split("\\.");
            toReturn = userClasses[userClasses.length-1];
        }

        return toReturn;
    }

    @Override //Used to generate key word needed to pass object from activity to activity.
    public String getUserTypeString(User newUser)
    {
        String toReturn = "User Not Found";
        if (validateLoginAttempt(newUser)) {
            String[] userClasses = newUser.getClass().toString().split("\\.");
            toReturn = userClasses[userClasses.length - 1];
        }

        return toReturn;
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
    public void setCurrentUser(User newUser) {
        if (validateLoginAttempt(newUser)) {
            userPersistence.setCurrentUser(getUser(newUser.getID(), newUser.getPassword()));
        }
    }


    private User getUser(String userName, String password) {
        User toReturn = null;

        if (validateLoginAttempt(userName, password)) {
            boolean userFound = false;
            for (int i = 0; i < appCurrentUsers.size() && !userFound; i++)
            {
                if(appCurrentUsers.get(i).equals(new User(userName, password)))
                {
                    toReturn = appCurrentUsers.get(i);
                    userFound = true;
                }
            }
        }

        return toReturn;
    }

    private boolean validateDataBase(ArrayList<User> data) throws IllegalArgumentException {
        boolean valid = true;

        for(int i=0; i<data.size(); i++)
        {
            for(int j=0; j<data.size(); j++)
            {
                if(i!=j && data.get(i).equals(data.get(j)))
                {
                    throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
                }
            }
        }

        return valid;
    }
}
