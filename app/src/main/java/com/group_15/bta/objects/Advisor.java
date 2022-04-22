package com.group_15.bta.objects;

import java.io.Serializable;

/**
 * Advisor
 * class for the advisor user
 */
public class Advisor extends User implements Serializable {


    public Advisor(String newName, String newPassword) {
        super(newName, newPassword);

    }

    public Advisor(String id, String password, String name) {
        super(id, password, name);
    }
}
