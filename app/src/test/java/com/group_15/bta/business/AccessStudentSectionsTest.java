package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccessStudentSectionsTest {
    private AccessStudentSections accessStudentSections;

    @Before
    public void setUp() {
        this.accessStudentSections = new AccessStudentSections(new StudentSectionPersistenceStub());
    }

    @Test
    public void test1()
    {
        ArrayList<StudentSection> sections = accessStudentSections.getStudentSectionList();
        assertNotNull(sections);

        int currSize = sections.size();
        assertEquals("10", String.valueOf(currSize));

        StudentSection testSection = sections.get(0);
        ArrayList<StudentSection> studentsFound = accessStudentSections.getStudentsInSection(testSection.getSection().getSection());
        assertEquals("2",String.valueOf(studentsFound.size()));

        Section.availableSectionDays[] days = new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Friday};
        Section.availableSectionTimes time = Section.availableSectionTimes.afternoonBirdWithLongCommute;

        Section section = new Section("A01", "Sara", days, time, "Online", 10, 50, "COMP 4000","Computer Science");
        accessStudentSections.insertSection(new StudentSection("505", StudentSection.grades.F,section,  new Course("", "")));
        sections = accessStudentSections.getStudentSectionList();
        currSize = sections.size();
        assertEquals("11", String.valueOf(currSize));

        accessStudentSections.deleteSection(sections.get(4));
        accessStudentSections.deleteSection(sections.get(3));
        sections = accessStudentSections.getStudentSectionList();
        currSize = sections.size();
        assertEquals("9", String.valueOf(currSize));
    }
}