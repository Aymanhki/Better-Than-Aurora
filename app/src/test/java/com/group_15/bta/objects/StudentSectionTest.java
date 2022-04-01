package com.group_15.bta.objects;

import org.junit.Test;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.*;

public class StudentSectionTest {
    @Test
    public void testStudentSection(){
        StudentSection studentSection;
        StudentSection studentSection2;

        Section section;

        String[] days = {"T","TR"};
        String[] time = {"10:00am", "11:00am"};
        section = new Section("A01",days,time,80);

        studentSection = new StudentSection("001", "A",section,  new Course("", ""));

        assertNotNull(studentSection);
        assertEquals("001", studentSection.getAssociatedStudent());
        assertEquals("A", studentSection.getGrade());
        assertTrue(studentSection.getSection().equals(section));

        studentSection2 = new StudentSection("002", "A",section, new Course("", ""));
        assertTrue(studentSection.equals(studentSection));
        assertFalse(studentSection.equals(studentSection2));
    }
}

