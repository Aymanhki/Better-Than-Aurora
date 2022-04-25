package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setupDatabase(){
        Student testStudent = new Student("student", "student", "Ayman", "B.Sc. (Hons)");
        Instructor testInstructor = new Instructor("instructor", "instructor", "Dr. Heather Matheson");
        Advisor testAdvisor = new Advisor("advisor", "advisor", "Velka");
        Administrator testAdmin = new Administrator("admin", "admin", "Sara");

        UserPersistence userPersistence = Services.getUserPersistence();
        ArrayList<User>  users = userPersistence.getUsers();

        if(!((ArrayList<?>) users).contains(testStudent))
        {
            userPersistence.insertUser(testStudent);
        }

        if(!users.contains(testInstructor))
        {
            userPersistence.insertUser(testInstructor);
        }

        if(!users.contains(testAdvisor))
        {
            userPersistence.insertUser(testAdvisor);
        }

        if(!users.contains(testAdmin))
        {
            userPersistence.insertUser(testAdmin);
        }
    }

    @Test
    public void testAllUsersLogIn()
    {
        onView(withId(R.id.userName)).perform(typeText("student"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.student_landing_page)).check(matches(isDisplayed()));
        pressBack();


        onView(withId(R.id.userName)).perform(typeText("instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.instructor_landing_page)).check(matches(isDisplayed()));
        pressBack();

        onView(withId(R.id.userName)).perform(typeText("advisor"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("advisor"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.advisor_landing_page)).check(matches(isDisplayed()));
        pressBack();


        onView(withId(R.id.userName)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.admin_landing_page)).check(matches(isDisplayed()));
        pressBack();

    }

}

