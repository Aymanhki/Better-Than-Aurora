package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CategoryListTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.persistence.SectionListTest;

public class AccessSectionsTest {
    private AccessSections accessSections;

    @Before
    public void setUp() {
        this.accessSections = new AccessSections(new SectionListTest());
    }

    @Test
    public void test1()
    {
        String [] days = new String[] {"M", "F"};
        String [] time = new String[] {"2:00 PM","3:00 PM"};

        ArrayList<Section> sections = accessSections.getSectionList();
        assertNotNull(sections);

        int currSize = sections.size();
        assertEquals("4", String.valueOf(currSize));

        ArrayList<Section> instructorSections = accessSections.getInstructorSections("Sara");
        assertEquals("Sara", instructorSections.get(0).getInstructor());

        ArrayList<Section> courseSections = accessSections.getCourseSections("COMP 4000");
        assertEquals("COMP 4000", courseSections.get(0).getAssociatedCourse());

        accessSections.insertSection(new Section("A05", days, time, 120));
        sections = accessSections.getSectionList();
        currSize = sections.size();
        assertEquals("5", String.valueOf(currSize));

        accessSections.deleteSection(sections.get(4));
        accessSections.deleteSection(sections.get(3));
        sections = accessSections.getSectionList();
        currSize = sections.size();
        assertEquals("3", String.valueOf(currSize));

    }
}
