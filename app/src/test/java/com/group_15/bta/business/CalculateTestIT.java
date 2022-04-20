package com.group_15.bta.business;

import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.HSQLDB.StudentPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.StudentSectionPersistenceHSQLDB;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.persistence.StudentSectionPersistence;
import com.group_15.bta.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CalculateTestIT {
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
    public void testCalculateGPA(){
        final ArrayList<Student> students = accessStudents.getStudentList();
        Student student = students.get(0);
        final ArrayList<StudentSection> sections = accessStudentSections.getStudentSectionList(student.getID(), false);

        assertTrue("3.0".equals(Calculate.gpa(sections)));
    }
}
