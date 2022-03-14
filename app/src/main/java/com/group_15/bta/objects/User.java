package com.group_15.bta.objects;

public class User implements IUser {
    protected String id;
    protected String password;
    protected String name;

    public User() {
    }

    public User(String newId, String newPassword) {
        id = newId;
        password = newPassword;
    }

    public User(String newId, String newPassword, String newName) {
        id = newId;
        password = newPassword;
        name = newName;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Boolean equals(User anotherUser)
    {
        return (this == anotherUser) || ( (id!=null && id.equals(anotherUser.id)) && ( password!=null && password.equals(anotherUser.password) ) );
    }


    public String getName() {
        return name;
    }
}
