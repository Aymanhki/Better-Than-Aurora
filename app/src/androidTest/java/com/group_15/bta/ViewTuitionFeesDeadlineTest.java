package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.DegreePersistence;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewTuitionFeesDeadlineTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        Degree testDegree = new Degree("Hello");
        Student testStudent = new Student("student5", "student5", "Ayman2", "Hello");

        DegreePersistence degreePersistence = Services.getDegreePersistence();
        ArrayList<Degree> degrees = degreePersistence.getDegreesList();

        if(!degrees.contains(testDegree))
        {
            degreePersistence.insert(testDegree);
        }
        degrees = degreePersistence.getDegreesList();
        assert degrees.contains(testDegree);


        UserPersistence userPersistence = Services.getUserPersistence();
        ArrayList<User>  users = userPersistence.getUsers();


        if(!users.contains(testStudent))
        {
            userPersistence.insertUser(testStudent);
        }

    }

    @Test
    public void viewTuitionFeesDeadlineTest()
    {
        onView(withId(R.id.userName)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());


        onView(withId(R.id.student_profile)).perform(click());
        onView(withId(R.id.show_summary)).perform(click());
        onView(withId(R.id.open_account_summary)).perform(click());
        onView(withId(R.id.total_payable)).check(matches(withText(containsString("May 6th, 2022"))));

        pressBack();
        pressBack();
        pressBack();

        onView(withId(R.id.student_settings)).perform(click());
        onView(withId(R.id.logout_student_account_btn)).perform(click());
    }
}
