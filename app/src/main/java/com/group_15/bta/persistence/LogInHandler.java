package com.group_15.bta.persistence;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.presentation.AdminMenuActivity;
import com.group_15.bta.presentation.AdvisorAccountActivity;
import com.group_15.bta.presentation.InstructorAccount;
import com.group_15.bta.presentation.StudentAccountActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class LogInHandler implements ILogInHandler{

    private final ArrayList<User> appCurrentUsers;
    public final String INVALID_DATA_MESSAGE  = "Multiple Users";

    public LogInHandler()
    {
        appCurrentUsers = new ArrayList<>();
    }

    public LogInHandler(User newUser)
    {
        appCurrentUsers = new ArrayList<>();
        appCurrentUsers.add(newUser);
    }

    public LogInHandler(ArrayList<User> newAppUsers)
    {
        if(validateDataBase(newAppUsers))
        {
            appCurrentUsers = newAppUsers;
        }
        else
        {
            appCurrentUsers = new ArrayList<>();
        }

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
                toReturn = InstructorAccount.class;
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
        if(validateLoginAttempt(newUser))
        {
            String[] userClasses = newUser.getClass().toString().split("\\.");
            toReturn = userClasses[userClasses.length-1];
        }

        return toReturn;
    }



    private User getUser(String userName, String password)
    {
        User toReturn = null;

        if(validateLoginAttempt(userName, password))
        {
            boolean userFound = false;
            for(int i=0; i<appCurrentUsers.size() && !userFound; i++)
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

    private boolean validateDataBase(ArrayList<User> data) throws IllegalArgumentException{
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
