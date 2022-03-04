package com.group_15.bta;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.persistence.SectionListData;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SectionListTest {
    private SectionListData sectionList;
    private ArrayList<Section> sections;

    @Before
    public void setUp(){
        sectionList = SectionListData.getInstance();
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

        sectionList.deleteSection(sections.size()-1);

        assertFalse(sections.contains(sec));

        System.out.println("\n Section List Test Finished");

    }
}
