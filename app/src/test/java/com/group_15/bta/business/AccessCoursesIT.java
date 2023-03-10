package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Course;
import com.group_15.bta.persistence.CoursePersistence;
import com.group_15.bta.persistence.HSQLDB.CoursePersistenceHSQLDB;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessCoursesIT {
    private AccessCourses accessCourses;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final CoursePersistence persistence = new CoursePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessCourses = new AccessCourses(persistence);
    }

    @Test
    public void testListCourses() {
        final Course course;

        course = accessCourses.getCourseList().get(0);
        assertNotNull("first sequential course should not be null", course);
        assertTrue("BIOL 1000".equals(course.getID()));

        System.out.println("Finished test AccessCourses");
    }

    @Test
    public void testGetCourses() {
        final ArrayList<Course> courses = accessCourses.getCourseList();
        assertEquals(14, courses.size());
    }

    @Test
    public void testInsertCourse() {
        final Course c = new Course("COMP 3190", "AI","An Introductory course to Artificial Intelligence",3,"Computer Science",1738.0,"B.Sc. (Hons)");
        accessCourses.insertCourses(c);
        assertEquals(15, accessCourses.getCourseList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}