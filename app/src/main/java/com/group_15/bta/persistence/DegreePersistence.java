package com.group_15.bta.persistence;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;

import java.util.ArrayList;

public interface DegreePersistence {
    void insert(Degree newDegree);
    ArrayList<Degree> getDegreesList();
    ArrayList<String> getDegreeListNames();
    boolean contains(Degree newDegree);
    ArrayList<Course> getDegreeCourses(String newDegree);
}
