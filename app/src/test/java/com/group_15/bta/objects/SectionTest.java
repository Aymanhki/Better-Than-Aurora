package com.group_15.bta.objects;

import org.junit.Test;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.*;

public class SectionTest  {

    @Test
    public void testSections(){
        Section section;
        Section section2;

        String[] days = {"T","TR"};
        String[] time = {"10:00am", "11:00am"};

        System.out.println("\nStarting Section Test");

        section = new Section("A01",days,time,80);
        assertNotNull(section);

        section2 = new Section("A02", "Sara", days, time, "Online", 10, 100, "COMP 1010", "Computer Science");
        assertNotNull(section);

        assertTrue("80".equals(section.getCap()));
        assertTrue("A01".equals(section.getSection()));
        assertTrue(days.equals(section.getDaysRaw()));
        assertTrue(time.equals(section.getTimes()));

        assertTrue("A02".equals(section2.getSection()));
        assertTrue("Sara".equals(section2.getInstructor()));
        assertTrue(days.equals(section2.getDaysRaw()));
        assertTrue(time.equals(section2.getTimes()));
        String testTime =  " " + time[0] + " - " + time[1];
        assertTrue(testTime.equals(section2.getTime()));
        assertTrue("Online".equals(section2.getLocation()));
        assertTrue("10".equals(String.valueOf(section2.getAvailable())));
        assertTrue("100".equals(String.valueOf(section2.getCAP())));
        assertTrue("COMP 1010".equals(section2.getAssociatedCourse()));
        assertTrue("Computer Science".equals(section2.getAssociatedCategory()));

        System.out.println("\nDone Section Test");
    }
}
