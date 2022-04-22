package com.group_15.bta;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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
public class DeleteCourseTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        SectionPersistence sectionPersist = Services.getSectionPersistence();
        ArrayList<Section> sections = sectionPersist.getSectionList();
        for(int i = 0; i<sections.size();i++){
            if(0 == sections.get(i).getSection().compareTo("BIOL 1000 - A01"))
            {
                break;
            }
            if(i == sections.size()-1)
            {
                Section.availableSectionDays[] days = new Section.availableSectionDays[3];
                days[0] = Section.availableSectionDays.Monday;
                days[1] = Section.availableSectionDays.Wednesday;
                days[2] = Section.availableSectionDays.Friday;
                Section.availableSectionTimes time = Section.availableSectionTimes.earlyBird;
                Section s = new Section("BIOL 1000 - A01", "Dr.Seuss",days,time,"Remote",
                        65,65,"BIOL 1000","Biological Sciences");
                sectionPersist.insertSection(s);
            }
        }
    }

    @Test
    public void deleteCourseTest(){
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

        onView(withId(R.id.DelSecID)).perform(typeText("A01"));
        closeSoftKeyboard();
        onView(withId(R.id.DelSection)).perform(click());

        pressBack();
        pressBack();
        pressBack();

        onView(withId(R.id.button4)).perform(click());

    }
}
