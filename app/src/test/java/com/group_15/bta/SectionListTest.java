package com.group_15.bta;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.business.AccessSections;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SectionListTest {
    private AccessSections sectionList;
    private ArrayList<Section> sections;

    @Before
    public void setUp(){
        sectionList = AccessSections.getInstance();
    }

    @Test
    public void test1(){

        System.out.println("\n Starting Section List Test");

         assertNull(sections);

         sections = sectionList.getSectionList();

         assertNotNull(sections);

        String[] days = {"T","TR"};
        String[] time = {"10:30am", "11:45am"};

        Section sec = new Section("A03",days,time,80);

        sectionList.insertSection(sec);

        assertTrue(sections.contains(sec));


        System.out.println("\n Section List Test Finished");

    }
}
