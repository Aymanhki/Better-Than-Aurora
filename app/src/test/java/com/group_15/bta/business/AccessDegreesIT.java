package com.group_15.bta.business;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.persistence.DegreePersistence;
import com.group_15.bta.persistence.HSQLDB.DegreePersistenceHSQLDB;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AccessDegreesIT {
    private AccessDegrees accessDegrees;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final DegreePersistence persistence = new DegreePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessDegrees = new AccessDegrees(persistence);
    }

    @Test
    public void insertTest()
    {
        accessDegrees.insert(new Degree("Test"));
        assert accessDegrees.contains(new Degree("Test"));
        System.out.println("Finished test insert Degree");
    }

    @Test
    public void containsTest()
    {
        assert accessDegrees.contains(new Degree("B.Sc. (Hons)"));
        System.out.println("Finished test contains Degree");
    }

    @Test
    public void getDegreeListTest()
    {
        ArrayList<Degree> degrees = accessDegrees.getDegreesList();
        assert degrees.contains(new Degree("B.Sc. (Hons)"));
        System.out.println("Finished test get Degrees list");
    }

    @Test
    public void getDegreeCoursesTest()
    {
        ArrayList<Course> courses = accessDegrees.getDegreeCourses("B.Sc. (Hons)");
        assert courses.get(new Random().nextInt(courses.size())).associatedWithDegree("B.Sc. (Hons)");
        System.out.println("Finished test get Degrees courses");
    }

    @Test
    public void getDegreeNames()
    {
        ArrayList<String> degreesNames = accessDegrees.getDegreeListNames();
        assert degreesNames.get(0).equals("B.Sc. (Hons)");
        System.out.println("Finished test get Degrees names");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
