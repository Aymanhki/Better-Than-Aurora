package com.group_15.bta;

import android.content.Intent;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.LogInHandler;
import com.group_15.bta.presentation.AdminMenuActivity;
import com.group_15.bta.presentation.StudentAccountActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests {

    LogInHandler testLogIn;
    Student testStudent = new Student();
    final String USER_SAMPLE = "Stew";
    final String PASS_SAMPLE = "Dent";
    Student testStudent2 = new Student(USER_SAMPLE, PASS_SAMPLE);
    Administrator testAdmin = new Administrator();
    Administrator testAdmin2 = new Administrator(USER_SAMPLE, PASS_SAMPLE);

    @Test
    public void validateInstanceAwareness()
    {
        //Testing the  instance awareness
        testLogIn = new LogInHandler(testStudent2);
        assertEquals(testLogIn.intendedActivity(USER_SAMPLE, PASS_SAMPLE), StudentAccountActivity.class);

        testLogIn = new LogInHandler(testAdmin2);
        assertEquals(testLogIn.intendedActivity(USER_SAMPLE, PASS_SAMPLE), AdminMenuActivity.class);
    }

    @Test
    public void testingPassingUsers()
    {
        //Testing passing the user from the login page to the account page.
        testLogIn = new LogInHandler(testStudent2);
        Intent intentTest = testLogIn.destinationIntent(USER_SAMPLE, PASS_SAMPLE, InstrumentationRegistry.getInstrumentation().getContext());
        assertEquals(testStudent2, intentTest.getSerializableExtra(testLogIn.getUserTypeString(testStudent2)));
    }


}

