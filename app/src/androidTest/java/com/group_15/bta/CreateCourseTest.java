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
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;


import com.group_15.bta.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class CreateCourseTest{
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        SectionPersistence sectionPersist = Services.getSectionPersistence();
        ArrayList<Section> sections = sectionPersist.getSectionList();
        for(int i = 0; i<sections.size();i++){
            if(0 == sections.get(i).getSection().compareTo("BIOL 1000 - A01"))
            {
                sectionPersist.deleteSection(sections.get(i));
            }
        }
    }

    @Test
    public void createCourseTest(){
        //login
        onView(withId(R.id.userName)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        //admin menu, courses
        onView(withId(R.id.button3)).perform(click());

        //Click Biological Science
        onData(anything()).inAdapterView(withId(R.id.CategoriesList)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.coursesList)).atPosition(0).perform(click());

        //create a section
        onView(withId(R.id.Instructor)).perform(typeText("Dr.Seuss"));
        closeSoftKeyboard();
        onView(withId(R.id.Location)).perform(typeText("Remote"));
        closeSoftKeyboard();
        onView(withId(R.id.SectionID)).perform(typeText("A01"));
        closeSoftKeyboard();
        onView(withId(R.id.CAP)).perform(typeText("65"));
        closeSoftKeyboard();
        onView(withId(R.id.section_time_picker)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_time_from_list)).atPosition(0).perform(click());
        onView(withId(R.id.Days)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_days_from_list)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_days_from_list)).atPosition(2).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_days_from_list)).atPosition(4).perform(click());
        onView(withId((R.id.done_selecting_days_btn))).perform(click());
        onView(withId(R.id.AddSection)).perform(click());

        //verify that it was actually created
        onData(anything()).inAdapterView(withId(R.id.sectionsList)).atPosition(0).onChildView(withId(R.id.section_name_list_item)).check(matches(withText("Section:BIOL 1000 - A01")));

        pressBack();
        pressBack();
        pressBack();

        onView(withId(R.id.button4)).perform(click());
    }

}
