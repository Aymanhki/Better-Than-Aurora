package com.group_15.bta.persistence;

import com.group_15.bta.objects.User;

import java.util.ArrayList;

public interface UserPersistence {
    ArrayList<User> getUsers();

    void insertUser(User newUser);

    void deleteUser(User toRemove);

    User getCurrentUser();

    void setCurrentUser(User newUser);
}
