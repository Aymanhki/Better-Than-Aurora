package com.group_15.bta.objects;

public interface IUser {
    String getID();

    String getPassword();

    Boolean equals(User anotherUser);

    String getName();
}
