package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchStudentAdvisorTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        StudentPersistence studentPersist = Services.getStudentPersistence();
        ArrayList<Student> student = studentPersist.getStudent(new Student("student"));
        studentPersist.updateStudent(new Student("student", "student","Ayman", "B.Sc. (Hons)"));
    }

    @Test
    public void searchStudent() {
        //login
        onView(withId(R.id.userName)).perform(typeText("advisor"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("advisor"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //advisor menu, search student accounts
        onView(withId(R.id.AdvisorStudentID)).perform(typeText("student"));
        onView(withId(R.id.button2)).perform(click());

        // verify correct student details are showing
        onView(withId(R.id.StudentName)).check(matches(withText("Student: Ayman")));

    }

}