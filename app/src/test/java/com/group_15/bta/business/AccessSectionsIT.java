package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;
import com.group_15.bta.persistence.HSQLDB.SectionPersistenceHSQLDB;
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
        assertTrue("COMP 3350 - A01".equals(section.getSection()));

    }

    @Test
    public void testGetSections() {
        final ArrayList<Section> sections = accessSections.getSectionList();
        assertEquals(2, sections.size());
    }

    @Test
    public void testGetInstructorSections() {
        final ArrayList<Section> sections = accessSections.getInstructorSections("Dr. Heather Matheson");
        assertEquals(2, sections.size());
    }



    @Test
    public void testDeleteSection() {
        final Section c = accessSections.getSectionList().get(1);
        ArrayList<Section> sections = accessSections.getSectionList();
        assertEquals(2, sections.size());
        accessSections.deleteSection(c);
        sections = accessSections.getSectionList();
        assertEquals(1, sections.size());
    }

    @Test
    public void testInsertSection() {
        String [] days = new String[] {"M", "F"};
        String [] time = new String[] {"2:00 PM","3:00 PM"};

        final Section c = new Section("A05", days, time, 120);
        accessSections.insertSection(c);
        assertEquals(3, accessSections.getSectionList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}