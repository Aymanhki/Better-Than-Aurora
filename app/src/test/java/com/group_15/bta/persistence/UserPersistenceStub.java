package com.group_15.bta.persistence;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.presentation.AdminMenuActivity;
import com.group_15.bta.presentation.AdvisorAccountActivity;
import com.group_15.bta.presentation.InstructorSectionsActivity;
import com.group_15.bta.presentation.StudentAccountActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class UserPersistenceStub implements UserPersistence {
    private ArrayList<User> users;
    private User currentUser = null;

    public UserPersistenceStub()
    {
        users = new ArrayList<>();
        users.add(new Student("student", "student", "Ayman"));
        users.add(new Student("student1", "student1", "Nilin"));
        users.add(new Student("student2", "student2", "Dara"));
        users.add(new Student("student3", "student3", "Amelia"));
        users.add(new Administrator("admin", "admin", "Sara"));
        users.add(new Administrator("admin1", "admin1", "Alex"));
        users.add(new Administrator("admin2", "admin2", "Gwynn"));
        users.add(new Administrator("admin3", "admin3", "Gwynevere"));
        users.add(new Advisor("advisor","advisor","Velka"));
        users.add(new Advisor("advisor1","advisor1","Adriandel"));
        users.add(new Advisor("advisor2","advisor2","Gotthand"));
        users.add(new Advisor("advisor3","advisor3","Kamui"));
        users.add(new Instructor("instructor","instructor","Sara"));
        users.add(new Instructor("instructor1","instructor1","Dr. Heather Matheson"));
        users.add(new Instructor("instructor2","instructor2","Siegward"));
        users.add(new Instructor("instructor3","instructor3","Vilhelm"));
    }




    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void insertUser(User newUser) {
        users.add(newUser);
    }

    @Override
    public void deleteUser(User toRemove) {
        users.remove(toRemove);
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User newUser) {
            currentUser = newUser;
    }




}
