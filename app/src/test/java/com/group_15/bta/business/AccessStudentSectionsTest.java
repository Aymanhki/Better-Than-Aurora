package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.CategoryListTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.persistence.StudentListTest;
import com.group_15.bta.persistence.StudentSectionListTest;

public class AccessStudentSectionsTest {
    private AccessStudentSections accessStudentSections;

    @Before
    public void setUp() {
        this.accessStudentSections = new AccessStudentSections(new StudentSectionListTest());
    }

    @Test
    public void test1()
    {
        ArrayList<StudentSection> sections = accessStudentSections.getSectionList();
        assertNotNull(sections);

        int currSize = sections.size();
        assertEquals("4", String.valueOf(currSize));

        StudentSection testSection = sections.get(0);
        ArrayList<StudentSection> studentsFound = accessStudentSections.getStudentsInSection(testSection.getSection().getSection());
        assertEquals("4",String.valueOf(studentsFound.size()));

        String [] days = new String[] {"M", "F"};
        String [] time = new String[] {"2:00 PM","3:00 PM"};

        Section section = new Section("A01", "Sara", days, time, "Online", 10, 50, "COMP 4000","Computer Science");
        accessStudentSections.insertSection(new StudentSection("505", "F",section));
        sections = accessStudentSections.getSectionList();
        currSize = sections.size();
        assertEquals("5", String.valueOf(currSize));

        accessStudentSections.deleteSection(sections.get(4));
        accessStudentSections.deleteSection(sections.get(3));
        sections = accessStudentSections.getSectionList();
        currSize = sections.size();
        assertEquals("3", String.valueOf(currSize));

    }
}