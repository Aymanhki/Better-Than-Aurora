package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StudentSectionTest {
    @Test
    public void testStudentSection(){
        StudentSection studentSection;
        StudentSection studentSection2;

        Section section;

        Section.availableSectionDays[] days = {Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday};
        Section.availableSectionTimes time = Section.availableSectionTimes.barelyEarlyBird;
        section = new Section("A01",days, time,80);

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

