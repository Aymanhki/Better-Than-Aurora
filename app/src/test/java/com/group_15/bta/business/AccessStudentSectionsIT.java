package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Student;
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
    public void testGetStudentSections() {
        final ArrayList<StudentSection> studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(1, studentSections.size());
    }

    @Test
    public void testGetStudentsInSection() {
        final ArrayList<StudentSection> students = accessStudentSections.getStudentsInSection("COMP 3350 - A01");
        assertEquals(1, students.size());
    }
    @Test
    public void testDeleteStudentSection() {
        final StudentSection c = accessStudentSections.getStudentSectionList().get(0);
        ArrayList<StudentSection> studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(1, studentSections.size());
        accessStudentSections.deleteSection(c);
        studentSections = accessStudentSections.getStudentSectionList();
        assertEquals(0, studentSections.size());
    }

    @Test
    public void testInsertStudentSection() {
        final ArrayList<StudentSection> studentSections = accessStudentSections.getStudentSectionList();
        Student newStudent = new Student("student2", "2", "Sara");
        accessStudents.insertStudent(newStudent);
        final StudentSection c = new StudentSection("student2", "A", studentSections.get(0).getSection(), new Course("", ""));
        accessStudentSections.insertSection(c);
        assertEquals(2, accessStudentSections.getStudentSectionList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
