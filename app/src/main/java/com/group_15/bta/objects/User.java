package com.group_15.bta.objects;

import com.group_15.bta.persistence.IUser;

public class User implements IUser
{
    private String id;
    private String password;
    private String type;

    public User(){}
    public User(String newName, String newPassword)
    {
        id = newName;
        password = newPassword;
    }

    @Override
    public String getName()
    {
        return id;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public Boolean equals(User anotherUser)
    {
        return (this == anotherUser) || ( (id!=null && id.equals(anotherUser.id)) && ( password!=null && password.equals(anotherUser.password) ) );
    }



}
