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
import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.CategoryPersistence;
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
public class FullTimeStudentEnrollmentTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        Degree testDegree = new Degree("Hello");
        Category testCategory = new Category("AAAAAAAAAAA");
        Student testStudent = new Student("student5", "student5", "Ayman2", "Hello");

        Course testCourse = new Course("Hello 101", "Hi", "Teaches you how to say hello", 3, "AAAAAAAAAAA", 1782.25, "Hello");
        Course testCourseToPartTime = new Course("Hi 1010", "Hi Again", "Teaches you how to say hello with more words", 3, "AAAAAAAAAAA", 1782.25, "Hello");
        Course testCourseToIncrease = new Course("Bye 101", "See you", "Teaches you how to say bye", 3, "AAAAAAAAAAA", 1782.25, "Hello");
        Course testCourseToMaximize = new Course("Bye 1010", "Goodbye", "Teaches you how to say bye with more words", 3, "AAAAAAAAAAA", 1782.25, "Hello");
        Course testCourseToFullTime = new Course("No 101", "No", "Teaches you how to say no", 3, "AAAAAAAAAAA", 1782.25, "Hello");
        Course testCourseToReject = new Course("No 1010", "Not Again", "Teaches you how to say no with more words", 3, "AAAAAAAAAAA", 1782.25, "Hello");

        Section testSection = new Section("Hello 101 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Monday}, Section.availableSectionTimes.earlyBird, "Remote", 500, 500, "Hello 101", "AAAAAAAAAAA");
        Section testSectionToPartTime = new Section("Hi 1010 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.perfectEarlyBird, "Remote", 500, 500, "Hi 1010", "AAAAAAAAAAA");
        Section testSectionToIncrease = new Section("Bye 101 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 500, 500, "Bye 101", "AAAAAAAAAAA");
        Section testSectionToMaximize = new Section("Bye 1010 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 500, 500, "Bye 1010", "AAAAAAAAAAA");
        Section testSectionToFullTime = new Section("No 101 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 500, 500, "No 101", "AAAAAAAAAAA");
        Section testSectionToReject = new Section("No 1010 - A01", "test instructor", new Section.availableSectionDays[]{Section.availableSectionDays.Saturday}, Section.availableSectionTimes.lateOwl, "Remote", 500, 500, "No 1010", "AAAAAAAAAAA");


        DegreePersistence degreePersistence = Services.getDegreePersistence();
        ArrayList<Degree> degrees = degreePersistence.getDegreesList();

        if(!degrees.contains(testDegree))
        {
            degreePersistence.insert(testDegree);
        }
        degrees = degreePersistence.getDegreesList();
        assert degrees.contains(testDegree);

        CategoryPersistence categoryPersistence =  Services.getCategoryPersistence();
        ArrayList<Category> categories = categoryPersistence.getCategoryList();

        if(!categories.contains(testCategory))
        {
            categoryPersistence.insertCategory(testCategory);
        }

        CoursePersistence coursePersistence = Services.getCoursePersistence();
        ArrayList<Course> courses = coursePersistence.getCourseList();

        if(!courses.contains(testCourse))
        {
            coursePersistence.insertCourses(testCourse);
        }

        if(!courses.contains(testCourseToPartTime))
        {
            coursePersistence.insertCourses(testCourseToPartTime);
        }

        if(!courses.contains(testCourseToIncrease))
        {
            coursePersistence.insertCourses(testCourseToIncrease);
        }

        if(!courses.contains(testCourseToMaximize))
        {
            coursePersistence.insertCourses(testCourseToMaximize);
        }

        if(!courses.contains(testCourseToFullTime))
        {
            coursePersistence.insertCourses(testCourseToFullTime);
        }

        if(!courses.contains(testCourseToReject))
        {
            coursePersistence.insertCourses(testCourseToReject);
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

        if(!sections.contains(testSectionToPartTime))
        {
            sectionPersistence.insertSection(testSectionToPartTime);
        }

        if(!sections.contains(testSectionToIncrease))
        {
            sectionPersistence.insertSection(testSectionToIncrease);
        }

        if(!sections.contains(testSectionToMaximize))
        {
            sectionPersistence.insertSection(testSectionToMaximize);
        }

        if(!sections.contains(testSectionToFullTime))
        {
            sectionPersistence.insertSection(testSectionToFullTime);
        }

        if(!sections.contains(testSectionToReject))
        {
            sectionPersistence.insertSection(testSectionToReject);
        }
    }

    @Test
    public void fullTineStudentEnrollmentTest()
    {
        onView(withId(R.id.userName)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        for(int i=0; i<=5; i++)
        {
            onView(withId(R.id.student_courses)).perform(click());
            onView(withId(R.id.add_or_drop_courses_btn)).perform(click());
            onView(withId(R.id.add_courses_btn)).perform(click());
            onData(anything()).inAdapterView(withId(R.id.categories_list_in_add_courses_fragment)).atPosition(0).perform(click());
            onData(anything()).inAdapterView(withId(R.id.courses_list_in_add_courses)).atPosition(i).perform(click());
            onData(anything()).inAdapterView(withId(R.id.sections_list_to_be_added)).atPosition(0).perform(click());
            onView(withId(R.id.add_section_btn)).perform(click());
        }

        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeUp());
        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeUp());

        onData(anything()).inAdapterView(withId(R.id.student_required_courses)).atPosition(0).onChildView(withId(R.id.course_code_list_item)).check(matches(withText(containsString("No 1010"))));

        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeDown());
        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeDown());

        for(int i=0; i<5; i++)
        {
            onData(anything()).inAdapterView(withId(R.id.student_enrolled_courses)).atPosition(0).perform(click());
            onView(withId(R.id.drop_section_btn)).perform(click());
        }

        onView(withId(R.id.student_settings)).perform(click());
        onView(withId(R.id.logout_student_account_btn)).perform(click());
    }
}
