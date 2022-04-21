package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SectionTest  {

    @Test
    public void testSections(){
        Section section;
        Section section2;

        Section.availableSectionDays[] days = {Section.availableSectionDays.Monday, Section.availableSectionDays.Thursday};
        Section.availableSectionTimes time = Section.availableSectionTimes.barelyEarlyBird;

        System.out.println("\nStarting Section Test");

        section = new Section("A01",days, time,80);
        assertNotNull(section);

        section2 = new Section("A02", "Sara", days, time, "Online", 10, 100, "COMP 1010", "Computer Science");
        assertNotNull(section);

        assertEquals("80", section.getCap());
        assertEquals("A01", section.getSection());
        assertEquals(days, section.getDaysRaw());
        assertEquals(time, section.getTime());

        assertEquals("A02", section2.getSection());
        assertEquals("Sara", section2.getInstructor());
        assertEquals(days, section2.getDaysRaw());
        assertEquals(time, section2.getTime());

        assertEquals("Online", section2.getLocation());
        assertEquals("10", String.valueOf(section2.getAvailable()));
        assertEquals("100", String.valueOf(section2.getCAP()));
        assertEquals("COMP 1010", section2.getAssociatedCourse());
        assertEquals("Computer Science", section2.getAssociatedCategory());

        System.out.println("\nDone Section Test");
    }
}
