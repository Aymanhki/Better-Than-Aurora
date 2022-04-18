package com.group_15.bta;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Student;

import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests {

    AccessUsers testLogIn;
    Student testStudent = new Student();
    final String USER_SAMPLE = "Stew";
    final String PASS_SAMPLE = "Dent";
    Student testStudent2 = new Student(USER_SAMPLE, PASS_SAMPLE);
    Administrator testAdmin = new Administrator();
    Administrator testAdmin2 = new Administrator(USER_SAMPLE, PASS_SAMPLE);



}

