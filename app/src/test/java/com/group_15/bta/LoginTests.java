package com.group_15.bta;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Student;

import org.junit.Test;

public class LoginTests {

    AccessUsers testLogIn;
    Student testStudent = new Student();
    final String USER_SAMPLE = "Stew";
    final String PASS_SAMPLE = "Dent";
    Student testStudent2 = new Student(USER_SAMPLE, PASS_SAMPLE);
    Administrator testAdmin = new Administrator();
    Administrator testAdmin2 = new Administrator(USER_SAMPLE, PASS_SAMPLE);
    final String STUDENT_KEY = "Student";
    final String ADMIN_KEY = "Administrator";


    @Test
    public void validateLoginAttempt()
    {
        //Testing the validity of an existing instance of a user
        testLogIn = new AccessUsers(testStudent);
        assertTrue(testLogIn.validateLoginAttempt(testStudent));

        //Testing the validity of a user using their credentials.
        testLogIn = new AccessUsers(testStudent2);
        assertTrue(testLogIn.validateLoginAttempt(new Student("Stew", "Dent")));

        //Testing the validity of a non existing instance of a user
        assertFalse(testLogIn.validateLoginAttempt(new Student()));

        //Testing the validity of a non existing user
        assertFalse(testLogIn.validateLoginAttempt(testAdmin));
    }



}
