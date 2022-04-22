package com.group_15.bta.objects;
/**
 * Class for User object
 * used to store the id, name, and password for a user.
 */
public class User implements IUser {
    protected String id;
    protected String password;
    protected String name;

    //constructors
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

    //getters
    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    //equals
    @Override
    public Boolean equals(User anotherUser)
    {
        return (this == anotherUser) || ( (id!=null && id.equals(anotherUser.id)) && ( password!=null && password.equals(anotherUser.password) ) );
    }



}
