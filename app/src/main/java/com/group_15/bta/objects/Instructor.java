package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Instructor extends User implements Serializable {

    public Instructor(String newName, String newPassword) {
        super(newName, newPassword);

    }

    public Instructor(String id, String password, String name) {
        super(id, password, name);
    }

    public boolean equals(final Instructor o) {
        return Objects.equals(this.id, o.id) &&
                Objects.equals(this.password, o.password) &&
                Objects.equals(this.name, o.name);
    }

    public String getName() { return this.name;}
}
