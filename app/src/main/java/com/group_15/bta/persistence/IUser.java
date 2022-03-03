package com.group_15.bta.persistence;

public interface IUser {
    String getName();
    String getPassword();
    Boolean equals(User anotherUser);

}
