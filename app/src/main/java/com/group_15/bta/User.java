package com.group_15.bta;

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
        Boolean equal = false;

        if(id.equals(anotherUser.id) && password.equals(anotherUser.password))
        {
            equal = true;
        }

        return equal;
    }


}
