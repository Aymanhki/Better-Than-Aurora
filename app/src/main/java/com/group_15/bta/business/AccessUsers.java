package com.group_15.bta.business;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;

import java.util.ArrayList;
/**
 * AccessUsers
 * Class to access users in database.
 */
public class AccessUsers implements UserPersistence {

    //instance of user database
    private UserPersistence userPersistence;

    //constructor, to start/set database
    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
    }

    //constructor, to set database when it has already been started
    public AccessUsers(final UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }


    /**
     * destinationIntent
     * sets which account a user log ins to and continues if log in is valid
     * @param userName - username entered in log in
     * @param password - password entered in log in
     * @param currentActivity - activity to go to if log in is valid
     * @return
     */
    @Override
    public Intent destinationIntent(String userName, String password, Context currentActivity) {
        return userPersistence.destinationIntent(userName, password, currentActivity);
    }

    /**
     * intendedActivity
     * sets the activity a user is trying to log in to (admin,student,advisor,instructor)
     * @param userName - username entered in log in
     * @param password - password entered in log in
     * @return - the activity that will start if log in is valid
     */
    @Override
    public Class intendedActivity(String userName, String password) {
        return userPersistence.intendedActivity(userName, password);
    }

    /**
     * validateLoginAttempt
     * validates a log in attempt
     * @param userName - name of user to validate
     * @param password - password of user to validate
     * @return - return true if valid user, false if not
     */
    @Override
    public boolean validateLoginAttempt(String userName, String password) {
        return userPersistence.validateLoginAttempt(userName, password);
    }

    /**
     * getUsers
     * returns the users that are in the database
     * @return an arraylist of the users in the database
     */
    @Override
    public ArrayList<User> getUsers() {
        return userPersistence.getUsers();
    }

    /**
     * insertUser
     * inserts a user into the database
     * @param newUser - user to be inserted
     */
    @Override
    public void insertUser(User newUser) {
        userPersistence.insertUser(newUser);
    }

    /**
     * deleteUser
     * deletes a user from the database
     * @param toRemove - user to be deleted
     */
    @Override
    public void deleteUser(User toRemove) {
        userPersistence.deleteUser(toRemove);
    }

    /**
     * getCurrentUser
     * get the user current logged in
     * @return - the user logged in
     */
    @Override
    public User getCurrentUser() {
        return userPersistence.getCurrentUser();
    }

    /**
     * setCurrentUser
     * logs in the user and sets them as the current user
     * @param userName - name of the current user
     * @param password - password of the current user
     */
    @Override
    public void setCurrentUser(String userName, String password) {
        userPersistence.setCurrentUser(userName, password);
    }

    /**
     * getUser
     * given a username and password, return that user
     * @param userName - name of user
     * @param password - password of user
     * @return - the user
     */
    @Override
    public User getUser(String userName, String password) {
          return userPersistence.getUser(userName, password);
    }

}
