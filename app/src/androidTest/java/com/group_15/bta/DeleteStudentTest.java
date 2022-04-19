package com.group_15.bta;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.presentation.MainActivity;
import com.group_15.bta.R;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeleteStudentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        // add a student that will appear at top of the list
        StudentPersistence studentPersist = Services.getStudentPersistence();
        studentPersist.insertStudent(new Student("A", "A","Misa", "B.Sc. (Hons)"));
    }


    @Test
    public void deleteStudent() {
        //login
        onView(withId(R.id.userName)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //admin menu, student accounts
        onView(withId(R.id.button2)).perform(click());

        //enter student id to delete
        onView(withId(R.id.DeleteStudentID)).perform(typeText("A"));

        //student list, delete student
        onView(withId(R.id.DeleteStudentButton)).perform(click());

        // verify that it was deleted
        onData(anything()).inAdapterView(withId(R.id.listStudent)).atPosition(0).perform(click());
        onView(withText("A")) .check(doesNotExist());

        //go back to login
        pressBack();
        pressBack();
        pressBack();

        //check cannot login with deleted student
        onView(withId(R.id.userName)).perform(typeText("A"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("A"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.student_settings)).check(doesNotExist());

    }

}
