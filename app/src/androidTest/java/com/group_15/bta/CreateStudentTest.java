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
public class CreateStudentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){

        StudentPersistence studentPersist = Services.getStudentPersistence();
        ArrayList<Student> student = studentPersist.getStudent(new Student("student3"));
        for (Student s: student)
            if (s.getID().equals("student3"))
                studentPersist.deleteStudent(s);
    }

    @Test
    public void createStudent() {
        //login
        onView(withId(R.id.userName)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //admin menu, student accounts
        onView(withId(R.id.button2)).perform(click());

        //student list, add student
        onView(withId(R.id.AddStudent)).perform(click());

        //create a student
        onView(withId(R.id.newName)).perform(typeText("Amelia"));
        closeSoftKeyboard();
        onView(withId(R.id.newID)).perform(typeText("student3"));
        closeSoftKeyboard();
        onView(withId(R.id.newPassword)).perform(typeText("student3"));
        closeSoftKeyboard();
        onView(withId(R.id.studentDegree)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_a_degree_from_list)).atPosition(0).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.createStudent)).perform(click());

        // verify that it was added
        onData(anything()).inAdapterView(withId(R.id.listStudent)).atPosition(3).perform(click());
        onView(withId(R.id.editStudentName)).check(matches(withText("Amelia")));

        //go back to login
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();

        //check can login with created student
        onView(withId(R.id.userName)).perform(typeText("student3"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student3"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.student_settings)).perform(click());

    }

}
