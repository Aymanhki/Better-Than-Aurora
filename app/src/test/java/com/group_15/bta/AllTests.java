package com.group_15.bta;


import com.group_15.bta.business.AccessCategoriesTest;
import com.group_15.bta.business.AccessCoursesTest;
import com.group_15.bta.business.AccessDegreesTest;
import com.group_15.bta.business.AccessSectionsTest;
import com.group_15.bta.business.AccessStudentSectionsTest;
import com.group_15.bta.business.AccessStudentsTest;
import com.group_15.bta.objects.AdministratorTest;
import com.group_15.bta.objects.AdvisorTest;
import com.group_15.bta.objects.CategoryTest;
import com.group_15.bta.objects.CourseTest;
import com.group_15.bta.objects.InstructorTest;
import com.group_15.bta.objects.SectionTest;
import com.group_15.bta.objects.StudentSectionTest;
import com.group_15.bta.objects.StudentTest;
import com.group_15.bta.objects.UserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCategoriesTest.class,
        AccessCoursesTest.class,
        AccessSectionsTest.class,
        AccessStudentSectionsTest.class,
        AccessStudentsTest.class,
        AdministratorTest.class,
        AccessDegreesTest.class,
        AdvisorTest.class,
        CategoryTest.class,
        CourseTest.class,
        InstructorTest.class,
        SectionTest.class,
        StudentSectionTest.class,
        StudentTest.class,
        UserTest.class
})
public class AllTests {}


