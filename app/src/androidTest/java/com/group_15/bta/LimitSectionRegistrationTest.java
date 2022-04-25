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
import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.CategoryPersistence;
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
public class LimitSectionRegistrationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        Degree testDegree = new Degree("Hello");
        Category testCategory = new Category("AAAAAAAAAAA");
        Administrator testAdmin = new Administrator("admin5", "admin5", "Ayman5");
        Student testStudent = new Student("student5", "student5", "Ayman2", "Hello");
        Student testStudentToReject = new Student("student6", "student6", "Ayman7", "Hello");

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

        UserPersistence userPersistence = Services.getUserPersistence();
        ArrayList<User>  users = userPersistence.getUsers();


        if(!users.contains(testStudent))
        {
            userPersistence.insertUser(testStudent);
        }

        if(!users.contains(testStudentToReject))
        {
            userPersistence.insertUser(testStudentToReject);
        }

        if(!users.contains(testAdmin))
        {
            userPersistence.insertUser(testAdmin);
        }
    }

    @Test
    public void limitSectionRegistrationTest()
    {
        onView(withId(R.id.userName)).perform(typeText("admin5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.button3)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.CategoriesList)).atPosition(0).perform(click());


        onView(withId(R.id.CourseID)).perform(typeText("Hello 101"));
        closeSoftKeyboard();
        onView(withId(R.id.CourseName)).perform(typeText("Hi"));
        closeSoftKeyboard();
        onView(withId(R.id.CourseCreditHours)).perform(typeText("3"));
        closeSoftKeyboard();
        onView(withId(R.id.CourseDescription)).perform(typeText("Teaches you how to say hi"));
        closeSoftKeyboard();
        onView(withId(R.id.courseDegree)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_degrees_list)).atPosition(1).perform(click());
        onView(withId((R.id.done_selecting_degrees))).perform(click());
        onView(withId(R.id.TuitionFee)).perform(typeText("1782.25"));
        closeSoftKeyboard();
        onView(withId(R.id.AddCourse)).perform(click());
        closeSoftKeyboard();

        onData(anything()).inAdapterView(withId(R.id.coursesList)).atPosition(0).perform(click());
        onView(withId(R.id.Instructor)).perform(typeText("test instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.Location)).perform(typeText("Remote"));
        closeSoftKeyboard();
        onView(withId(R.id.SectionID)).perform(typeText("A01"));
        closeSoftKeyboard();
        onView(withId(R.id.CAP)).perform(typeText("1"));
        closeSoftKeyboard();
        onView(withId(R.id.section_time_picker)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_time_from_list)).atPosition(0).perform(click());
        onView(withId(R.id.Days)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.to_select_days_from_list)).atPosition(0).perform(click());
        onView(withId((R.id.done_selecting_days_btn))).perform(click());
        onView(withId(R.id.AddSection)).perform(click());
        closeSoftKeyboard();

        pressBack();
        pressBack();
        pressBack();
        pressBack();

        onView(withId(R.id.userName)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.student_courses)).perform(click());
        onView(withId(R.id.add_or_drop_courses_btn)).perform(click());
        onView(withId(R.id.add_courses_btn)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categories_list_in_add_courses_fragment)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses_list_in_add_courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.sections_list_to_be_added)).atPosition(0).perform(click());
        onView(withId(R.id.add_section_btn)).perform(click());

        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();


        onView(withId(R.id.userName)).perform(typeText("student6"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("student6"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.student_courses)).perform(click());
        onView(withId(R.id.add_or_drop_courses_btn)).perform(click());
        onView(withId(R.id.add_courses_btn)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categories_list_in_add_courses_fragment)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses_list_in_add_courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.sections_list_to_be_added)).atPosition(0).perform(click());
        onView(withId(R.id.add_section_btn)).perform(click());

        onView(ViewMatchers.withId(R.id.student_landing_page)).perform(ViewActions.swipeUp());
        onData(anything()).inAdapterView(withId(R.id.student_required_courses)).atPosition(0).onChildView(withId(R.id.course_code_list_item)).check(matches(withText(containsString("Hello 101"))));

        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();
        pressBack();


        onView(withId(R.id.userName)).perform(typeText("admin5"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("admin5"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.button3)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.CategoriesList)).atPosition(0).perform(click());
        onView(withId(R.id.DeleteCourseID)).perform(typeText("Hello 101"));
        closeSoftKeyboard();
        onView(withId(R.id.DeleteCourseButton)).perform(click());

    }

}
