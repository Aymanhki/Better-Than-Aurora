package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.persistence.HSQLDB.StudentPersistenceHSQLDB;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessStudentsIT {
    private AccessStudents accessStudents;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final StudentPersistence persistence = new StudentPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessStudents = new AccessStudents(persistence);
    }

    @Test
    public void testListStudents() {
        final Student student;

        student = accessStudents.getStudentList().get(0);
        assertNotNull("first sequential course should not be null", student);
        assertTrue("Ayman".equals(student.getName()));

    }

    @Test
    public void testGetStudents() {
        final ArrayList<Student> students = accessStudents.getStudentList();
        assertEquals(1, students.size());
    }

    @Test
    public void testGetStudent() {
        ArrayList<Student> students = accessStudents.getStudentList();
        final ArrayList<Student> student = accessStudents.getStudent(students.get(0));
        assertEquals(1, student.size());
    }

    @Test
    public void testDeleteStudent() {
        final Student c = accessStudents.getStudentList().get(0);
        ArrayList<Student> students = accessStudents.getStudentList();
        assertEquals(1, students.size());
        accessStudents.deleteStudent(c);
        students = accessStudents.getStudentList();
        assertEquals(0, students.size());
    }

    @Test
    public void testInsertStudent() {
        final Student c = new Student("student2", "2", "Sara");
        accessStudents.insertStudent(c);
        assertEquals(2, accessStudents.getStudentList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
