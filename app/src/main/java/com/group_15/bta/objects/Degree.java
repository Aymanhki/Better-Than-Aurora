package com.group_15.bta.objects;

/**
 * Degree
 * class for degree
 */
public class Degree {
    private final String name;

    public Degree(String newName)
    {
        name = newName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        return name.equals(((Degree)o).name);
    }
}
