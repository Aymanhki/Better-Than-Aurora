package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.persistence.DegreePersistence;

import java.util.ArrayList;

/**
 * AccessDegrees
 * Class to access degrees in database.
 */
public class AccessDegrees implements DegreePersistence {
    //instance of degree database
    private final DegreePersistence degreePersistence;

    //constructor, to start/set database
    public AccessDegrees(){degreePersistence = Services.getDegreePersistence(); }
    public AccessDegrees(DegreePersistence degreePersistence){this.degreePersistence = degreePersistence; }

    /**
     * insert
     * inserts a degree into the database
     * @param newDegree - degree to be inserted
     */
    @Override
    public void insert(Degree newDegree) {
        degreePersistence.insert(newDegree);
    }

    /**
     * getDegreesList
     * returns the degrees that are in the database
     * @return an arraylist of the degrees in the database
     */
    @Override
    public ArrayList<Degree> getDegreesList() {
        return degreePersistence.getDegreesList();
    }

    /**
     * getDegreesListNames
     * returns the names of the degrees that are in the database
     * @return an arraylist of the names of the degrees in the database
     */
    @Override
    public ArrayList<String> getDegreeListNames() {
        return degreePersistence.getDegreeListNames();
    }

    /**
     * contains
     * returns true if a degree is currently in the database
     * @param newDegree - the degree we want to check is in the database
     * @return - true if degree is in database, false if not
     */
    @Override
    public boolean contains(Degree newDegree) {
        return degreePersistence.contains(newDegree);
    }

    /**
     * getDegreeCourses
     * returns an arraylist of courses that fall under a particular degree in the database
     * @param newDegree - a string, representing the name of the degree
     * @return an arraylist of courses that are needed for a degree
     */
    @Override
    public ArrayList<Course> getDegreeCourses(String newDegree) {
        return degreePersistence.getDegreeCourses(newDegree);
    }
}
