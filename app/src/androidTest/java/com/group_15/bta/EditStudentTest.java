package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

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
public class EditStudentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        // clear student3 from the database
        StudentPersistence studentPersist = Services.getStudentPersistence();
        ArrayList<Student> student = studentPersist.getStudent(new Student("student"));
        studentPersist.updateStudent(new Student("student", "student","Ayman", "B.Sc. (Hons)"));
    }

    @Test
    public void editStudent() {
        //login
        onView(withId(R.id.userName)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //admin menu, student accounts
        onView(withId(R.id.button2)).perform(click());

        //student list, click a student
        onData(anything()).inAdapterView(withId(R.id.listStudent)).atPosition(0).perform(click());

        //edit a student
        onView(withId(R.id.editStudentName)).perform(typeText("2"));
        closeSoftKeyboard();
        onView(withId(R.id.editStudentPassword)).perform(typeText("A"));
        closeSoftKeyboard();
        onView(withId(R.id.editStudent)).perform(click());

        // verify that it was edited
        onData(anything()).inAdapterView(withId(R.id.listStudent)).atPosition(0).perform(click());
        onView(withId(R.id.editStudentName)).check(matches(withText("Ayman2")));
        onView(withId(R.id.editStudentPassword)).check(matches(withText("studentA")));

        //go back to login
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();

        //check can login with created student
        onView(withId(R.id.userName)).perform(typeText("student"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("studentA"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.student_settings)).perform(click());

    }

}