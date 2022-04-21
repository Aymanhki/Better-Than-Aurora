package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccessSectionsTest {
    private AccessSections accessSections;

    @Before
    public void setUp() {
        this.accessSections = new AccessSections(new SectionPersistenceStub());
    }

    @Test
    public void test1()
    {
        Section.availableSectionDays[] days = new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Friday};
        Section.availableSectionTimes time = Section.availableSectionTimes.afternoonBirdWithLongCommute;

        ArrayList<Section> sections = accessSections.getSectionList();
        assertNotNull(sections);

        int currSize = sections.size();
        assertEquals("62", String.valueOf(currSize));

        ArrayList<Section> instructorSections = accessSections.getInstructorSections("Sara");
        assertEquals("Sara", instructorSections.get(0).getInstructor());

        ArrayList<Section> courseSections = accessSections.getCourseSections("COMP 3350");
        assertEquals("COMP 3350", courseSections.get(0).getAssociatedCourse());

        accessSections.insertSection(new Section("A05", days, time, 120));
        sections = accessSections.getSectionList();
        currSize = sections.size();
        assertEquals("63", String.valueOf(currSize));



    }
}
