package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.HSQLDB.SectionPersistenceHSQLDB;
import com.group_15.bta.persistence.SectionPersistence;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessSectionsIT {
    private AccessSections accessSections;
    private File tempDB;
    private int numSections=30;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final SectionPersistence persistence = new SectionPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessSections = new AccessSections(persistence);
    }

    @Test
    public void testListSections() {
        final Section section;

        section = accessSections.getSectionList().get(0);
        assertNotNull("first sequential course should not be null", section);
        assertTrue("BIOL 1010 - A01".equals(section.getSection()));

    }

    @Test
    public void testGetSections() {
        final ArrayList<Section> sections = accessSections.getSectionList();
        assertEquals(numSections, sections.size());
    }

    @Test
    public void testGetInstructorSections() {
        final ArrayList<Section> sections = accessSections.getInstructorSections("Dr. Heather Matheson");
        assertEquals(1, sections.size());
    }

    @Test
    public void testInsertSection() {
        Section.availableSectionDays[] days = new Section.availableSectionDays[] {Section.availableSectionDays.Monday, Section.availableSectionDays.Friday};
        Section.availableSectionTimes time = Section.availableSectionTimes.afternoonBirdWithLongCommute;

        final Section c = new Section("A05", days, time, 120);
        accessSections.insertSection(c);
        assertEquals(numSections+1, accessSections.getSectionList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}