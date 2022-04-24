package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.HSQLDB.StudentPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.StudentSectionPersistenceHSQLDB;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.persistence.StudentSectionPersistence;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessStudentSectionsIT {
    private AccessStudentSections accessStudentSections;
    private AccessStudents accessStudents;
    private File tempDB;
    private int numStudentSection=10;
    private int studentIn3350=2;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final StudentSectionPersistence persistence = new StudentSectionPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessStudentSections = new AccessStudentSections(persistence);
        final StudentPersistence persistenceStudent = new StudentPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessStudents = new AccessStudents(persistenceStudent);
    }

    @Test
    public void testListStudentSections() {
        final StudentSection studentSection;

        studentSection = accessStudentSections.getStudentSectionList().get(0);
        assertNotNull("first sequential course should not be null", studentSection);
        assertTrue("student".equals(studentSection.getAssociatedStudent()));

    }

    @Test
    public void testGetStudentCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        final StudentSection studentSection;

        studentSection = accessStudentSections.getStudentSectionList().get(0);
        String studentId = studentSection.getAssociatedStudent();
        courses = accessStudentSections.getCourses(studentId);
        assertNotNull("first sequential course should not be null", studentId);
        assertTrue("BIOL 1010".equals(courses.get(0).getID()));

    }

    @Test
    public void testGetStudentSections() {
        final ArrayList<StudentSection> studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(numStudentSection, studentSections.size());
    }

    @Test
    public void testGetStudentsInSection() {
        final ArrayList<StudentSection> students = accessStudentSections.getStudentsInSection("COMP 3350 - A01");
        assertEquals(studentIn3350, students.size());
    }
    @Test
    public void testDeleteStudentSection() {
        final StudentSection c = accessStudentSections.getStudentSectionList().get(0);
        ArrayList<StudentSection> studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(numStudentSection, studentSections.size());
        accessStudentSections.deleteSection(c);
        studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(numStudentSection-1, studentSections.size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
