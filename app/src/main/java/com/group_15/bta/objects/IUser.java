package com.group_15.bta.objects;

import com.group_15.bta.objects.User;

public interface IUser {
    String getID();

    String getPassword();

    Boolean equals(User anotherUser);

    String getName();
}
