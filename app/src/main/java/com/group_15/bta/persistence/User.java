package com.group_15.bta.persistence;

//class for users
public class User implements IUser
{
    private String id;
    private String password;
    private String type;

    //default constructor
    public User(){}

    //constructor
    public User(String newName, String newPassword)
    {
        id = newName;
        password = newPassword;
    }

    //getters
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

    //check equal
    @Override
    public Boolean equals(User anotherUser)
    {
        Boolean equal = false;

        if(id.equals(anotherUser.id) && password.equals(anotherUser.password))
        {
            equal = true;
        }

        return equal;
    }


}
