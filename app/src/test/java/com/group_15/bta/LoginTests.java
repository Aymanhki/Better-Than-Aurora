package com.group_15.bta;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.business.LogInHandler;
import com.group_15.bta.objects.User;


import java.util.ArrayList;

public class LoginTests {

    LogInHandler testLogIn;
    Student testStudent = new Student();
    final String USER_SAMPLE = "Stew";
    final String PASS_SAMPLE = "Dent";
    Student testStudent2 = new Student(USER_SAMPLE, PASS_SAMPLE);
    Administrator testAdmin = new Administrator();
    Administrator testAdmin2 = new Administrator(USER_SAMPLE, PASS_SAMPLE);
    final String STUDENT_KEY = "Student";
    final String ADMIN_KEY = "Administrator";

    @Rule
    public ExpectedException exception =  ExpectedException.none();

    @Before
    public void resetValues()
    {
        testLogIn = new LogInHandler();
    }

    @Test
    public void validateLoginAttempt()
    {
        //Testing the validity of an existing instance of a user
        testLogIn = new LogInHandler(testStudent);
        assertTrue(testLogIn.validateLoginAttempt(testStudent));

        //Testing the validity of a user using their credentials.
        testLogIn = new LogInHandler(testStudent2);
        assertTrue(testLogIn.validateLoginAttempt(new Student("Stew", "Dent")));

        //Testing the validity of a non existing instance of a user
        assertFalse(testLogIn.validateLoginAttempt(new Student()));

        //Testing the validity of a non existing user
        assertFalse(testLogIn.validateLoginAttempt(testAdmin));
    }

    @Test
    public void validateDataBase()
    {
        //Testing the validity of multiple users with the same credentials.
        ArrayList<User> testUsersDataBase = new ArrayList<>();
        testUsersDataBase.add(testAdmin2);
        testUsersDataBase.add(testStudent2);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(testLogIn.INVALID_DATA_MESSAGE);
        new LogInHandler(testUsersDataBase);
    }

    @Test
    public void getAccountKeyString()
    {
        //Asserting the log in handler can get the right key to pass account object to the next activity.
        testLogIn = new LogInHandler(testAdmin);
        assertEquals(testLogIn.getUserTypeString(testAdmin), ADMIN_KEY);

        testLogIn = new LogInHandler(testStudent);
        assertEquals(testLogIn.getUserTypeString(testStudent), STUDENT_KEY);
    }
}
