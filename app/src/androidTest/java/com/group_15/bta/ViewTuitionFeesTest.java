package com.group_15.bta;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.adevinta.android.barista.interaction.BaristaSleepInteractions;
import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.CoursePersistence;
import com.group_15.bta.persistence.DegreePersistence;
import com.group_15.bta.persistence.SectionPersistence;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewTuitionFeesTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDatabase(){
        Degree testDegree = new Degree("Hello");
        Student testStudent = new Student("student5", "student5", "Ayman2", "Hello");
        Course testCourse = new Course("Hello 101", "Hi", "Teaches you how to say hello", 3, "English", 1782.25, "Hello");
        Section testSection = new Section("Hello 101 - A01", "Me", new Section.availableSectionDays[]{Section.availableSectionDays.Monday}, Section.availableSectionTimes.earlyBird, "Remote", 500, 500, "Hello 101", "English");
        DegreePersistence degreePersistence = Services.getDegreePersistence();
        ArrayList<Degree> degrees = degreePersistence.getDegreesList();

        if(!degrees.contains(testDegree))
        {
            degreePersistence.insert(testDegree);
        }
        degrees = degreePersistence.getDegreesList();
        assert degrees.contains(testDegree);

        CoursePersistence coursePersistence = Services.getCoursePersistence();
        ArrayList<Course> courses = coursePersistence.getCourseList();

        if(!courses.contains(testCourse))
        {
            coursePersistence.insertCourses(testCourse);
        }

        UserPersistence userPersistence = Services.getUserPersistence();
        ArrayList<User>  users = userPersistence.getUsers();


        if(!users.contains(testStudent))
        {
            userPersistence.insertUser(testStudent);
        }

        SectionPersistence sectionPersistence = Services.getSectionPersistence();
        ArrayList<Section> sections = sectionPersistence.getSectionList();

        if(!sections.contains(testSection))
        {
            sectionPersistence.insertSection(testSection);
        }
    }

    @Test
    public void viewTuitionFessTest()
    {
        onView(withId(R.id.userName)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeUp());
        onData(anything()).inAdapterView(withId(R.id.student_required_courses)).atPosition(0).perform(click());
        BaristaSleepInteractions.sleep(2000);
        onView(withId(R.id.sections_list_to_be_added)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.sections_list_to_be_added)).atPosition(0).perform(click());
        onView(withId(R.id.add_section_btn)).perform(click());
        onView(withId(R.id.student_profile)).perform(click());
        onView(withId(R.id.show_summary)).perform(click());
        onView(withId(R.id.open_account_summary)).perform(click());
        onView(withId(R.id.tuition_fee_amount)).check(matches(withText(containsString("1782.25"))));

        pressBack();
        pressBack();
        pressBack();

        onData(anything()).inAdapterView(withId(R.id.student_enrolled_courses)).atPosition(0).perform(click());
        onView(withId(R.id.drop_section_btn)).perform(click());

    }


    public static boolean waitForElementUntilDisplayed(ViewInteraction element) {
        int i = 0;
        while (i++ < 5) {
            try {
                element.check(matches(isDisplayed()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (Exception e1) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }



}
