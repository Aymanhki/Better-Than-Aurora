package com.group_15.bta.objects;
/**
 * IUser
 * interface for user
 */
public interface IUser {
    String getID();

    String getPassword();

    Boolean equals(User anotherUser);

    String getName();
}
