package com.group_15.bta.persistence;

import com.group_15.bta.objects.User;

public interface IUser {
    String getName();
    String getPassword();
    Boolean equals(User anotherUser);

}
