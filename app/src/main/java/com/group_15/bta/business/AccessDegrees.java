package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.persistence.DegreePersistence;

import java.util.ArrayList;

public class AccessDegrees implements DegreePersistence {
    private DegreePersistence degreePersistence;

    public AccessDegrees(){degreePersistence = Services.getDegreePersistence(); }

    @Override
    public void insert(Degree newDegree) {
        degreePersistence.insert(newDegree);
    }

    @Override
    public ArrayList<Degree> getDegreesList() {
        return degreePersistence.getDegreesList();
    }

    @Override
    public ArrayList<String> getDegreeListNames() {
        return degreePersistence.getDegreeListNames();
    }

    @Override
    public boolean contains(Degree newDegree) {
        return degreePersistence.contains(newDegree);
    }

    @Override
    public ArrayList<Course> getDegreeCourses(String newDegree) {
        return degreePersistence.getDegreeCourses(newDegree);
    }
}
