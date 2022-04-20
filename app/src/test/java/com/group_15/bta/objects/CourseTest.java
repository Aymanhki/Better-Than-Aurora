package com.group_15.bta.objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

public class CourseTest {

    @Test
    public void testCourses(){

        Course courses;
        Course course2;
        Course course3;
        System.out.println("\nStarting Course Test");

        courses = new Course("Comp 2160", "Object Orientation");
        assertNotNull(courses);
        assertTrue("Comp 2160".equals(courses.getID()));
        assertTrue("Object Orientation".equals(courses.getTitle()));

        course2 = new Course("COMP 2160", "Object Orientation", "Objects and stuff.",3,"Computer Science", 1782.25, "B.Sc. (Hons)");
        assertNotNull(course2);
        assertTrue("Comp 2160".equals(courses.getID()));
        assertTrue("Object Orientation".equals(courses.getTitle()));
        assertTrue("Objects and stuff.".equals(course2.getDescription()));
        assertTrue("3".equals(String.valueOf(course2.getCreditHours())));
        assertTrue("Computer Science".equals(course2.getAssociatedCategory()));

        ArrayList<Section> sections = new ArrayList<>();
        String[] days = {"T","TR"};
        String time = "10:00am - 11:00am";
        Section section = new Section("A01",days, time,80);
        course3 = new Course("COMP 2160", "Object Orientation", "Objects and stuff.",sections);
        assertNotNull(course2);
        assertTrue("Comp 2160".equals(courses.getID()));
        assertTrue("Object Orientation".equals(courses.getTitle()));
        assertTrue("Objects and stuff.".equals(course2.getDescription()));
        assertTrue(course3.getSections().size() == 0);
        course3.addSection(section);
        assertTrue(course3.getSections().get(0).equals(section));
        course3.addSection("COMP 1010", days, time, 120);
        assertTrue(course3.getSections().size() == 2);

        System.out.println("\nDone Course Test");

    }


}
