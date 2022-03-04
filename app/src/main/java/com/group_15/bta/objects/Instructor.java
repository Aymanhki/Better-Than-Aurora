package com.group_15.bta.objects;

import com.group_15.bta.persistence.User;

import java.io.Serializable;

public class Instructor extends User implements Serializable {


    public Instructor(String newName, String newPassword) {
        super(newName, newPassword);

    }
}
