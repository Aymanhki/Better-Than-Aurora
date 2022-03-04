package com.group_15.bta;

import org.junit.Test;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.*;

public class SectionTest  {

    @Test
    public void test(){
        Section section;

        String[] days = {"T","TR"};
        String[] time = {"10:00am", "11:00am"};

        System.out.println("\nStarting Section Test");

        section = new Section("A01",days,time,80);
        assertNotNull(section);
        assertTrue("80".equals(section.getCap()));
        assertTrue("A01".equals(section.getSection()));
        assertTrue(days.equals(section.getDaysRaw()));
        assertTrue(time.equals(section.getTimes()));

        System.out.println("\nDone Section Test");
    }
}
