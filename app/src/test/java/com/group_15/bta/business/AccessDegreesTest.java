package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.persistence.DegreePersistence;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

public class AccessDegreesTest
{
    private AccessDegrees accessDegrees;
    private DegreePersistence degreePersistence;

    @Before
    public void setup()
    {
        degreePersistence = mock(DegreePersistence.class);
        accessDegrees = new AccessDegrees(degreePersistence);
    }

    @Test
    public void testAccessDegrees()
    {
        final Degree degree;
        final ArrayList<Degree> degrees = new ArrayList<>();
        degrees.add(new Degree("Test"));
        when(degreePersistence.getDegreesList()).thenReturn(degrees);
        degree = accessDegrees.getDegreesList().get(0);
        assertNotNull(degree);
        assertEquals("Test", degree.getName());
        verify(degreePersistence).getDegreesList();

        final Course course;
        final ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("test 101", "test test", "mock test", 3, "Mock", 1782.25, "Test"));
        when(degreePersistence.getDegreeCourses("Test")).thenReturn(courses);
        course = accessDegrees.getDegreeCourses("Test").get(0);
        assertNotNull(course);
        assertEquals("test 101", course.getID());
        assertEquals("test test", course.getTitle());
        assertEquals("mock test", course.getDescription());
        assertEquals(3, course.getCreditHours());
        assertEquals("Mock", course.getAssociatedCategory());
        assertEquals(String.valueOf(1782.25), String.valueOf(course.getTuition()));
        assertEquals("Test", course.getAssociatedDegree());
        verify(degreePersistence).getDegreeCourses("Test");

        final boolean found;
        when(degreePersistence.contains(new Degree("Test"))).thenReturn(true);
        found = accessDegrees.contains(new Degree("Test"));
        assert(found);
        verify(degreePersistence).contains(new Degree("Test"));

        final String degreeName;
        final ArrayList<String> degreeNames = new ArrayList<>();
        degreeNames.add("Test");
        when(degreePersistence.getDegreeListNames()).thenReturn(degreeNames);
        degreeName = degreePersistence.getDegreeListNames().get(0);
        assertNotNull(degreeName);
        assertEquals("Test", degreeName);
        verify(degreePersistence).getDegreeListNames();


        ArgumentCaptor<Degree> nameCapture = ArgumentCaptor.forClass(Degree.class);
        doNothing().when(degreePersistence).insert(nameCapture.capture());
        accessDegrees.insert(new Degree("Test"));
        assertEquals("Test", nameCapture.getValue().getName());
        verify(degreePersistence).insert(new Degree("Test"));
    }
}
