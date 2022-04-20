package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.StringStartsWith.startsWith;

import androidx.test.rule.ActivityTestRule;

import com.group_15.bta.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class UpdateGradeInstructorTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void updateGrade() {
        //login
        onView(withId(R.id.userName)).perform(typeText("instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //instructor menu, click my sections
        onView(withId(R.id.button2)).perform(click());

        // click section
        onData(anything()).inAdapterView(withId(R.id.listSections)).atPosition(0).perform(click());

        // click student and update grade
        onData(anything()).inAdapterView(withId(R.id.studentSectionList)).atPosition(0).perform(click());
        onView(withId(R.id.Grade)).perform(typeText("A+"));
        closeSoftKeyboard();

        onView(withId(R.id.UpdateGrade)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.studentSectionList))
                .atPosition(1)
                .check(matches(withText(startsWith("student: A+"))));
    }

}
