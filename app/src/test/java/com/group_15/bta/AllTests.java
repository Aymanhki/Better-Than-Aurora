package com.group_15.bta;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.business.AccessCategoriesTest;
import com.group_15.bta.business.AccessCoursesTest;
import com.group_15.bta.business.AccessSections;
import com.group_15.bta.business.AccessSectionsTest;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.AdministratorTest;
import com.group_15.bta.objects.AdvisorTest;
import com.group_15.bta.objects.CategoryTest;
import com.group_15.bta.objects.CoursesTest;
import com.group_15.bta.objects.InstructorTest;
import com.group_15.bta.objects.SectionTest;
import com.group_15.bta.objects.StudentSectionTest;
import com.group_15.bta.objects.StudentTest;
import com.group_15.bta.objects.UserTest;
import com.group_15.bta.business.AccessStudentsTest;
import com.group_15.bta.business.AccessStudentSectionsTest;
import com.group_15.bta.persistence.CategoryListTest;
import com.group_15.bta.persistence.CourseListTest;
import com.group_15.bta.persistence.StudentListTest;
import com.group_15.bta.persistence.SectionListTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCategoriesTest.class,
        AccessCoursesTest.class,
        AccessSectionsTest.class,
        AccessStudentSectionsTest.class,
        AccessStudentsTest.class,
        AdministratorTest.class,
        AdvisorTest.class,
        CategoryTest.class,
        CoursesTest.class,
        InstructorTest.class,
        SectionTest.class,
        StudentSectionTest.class,
        StudentTest.class,
        UserTest.class
})
public class AllTests {}


