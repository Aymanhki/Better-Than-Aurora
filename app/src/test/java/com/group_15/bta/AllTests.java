package com.group_15.bta;



import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.group_15.bta.LoginTests;
import com.group_15.bta.CategoryTest;
import com.group_15.bta.CategoryListTest;
import com.group_15.bta.CoursesTest;
import com.group_15.bta.CourseListTest;
import com.group_15.bta.SectionTest;
import com.group_15.bta.SectionListTest;
import com.group_15.bta.StudentTest;
import com.group_15.bta.StudentListTest;
import com.group_15.bta.objects.Student;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CategoryListTest.class,
        CategoryTest.class,
        CourseListTest.class,
        CoursesTest.class,
        LoginTests.class,
        SectionListTest.class,
        SectionTest.class,
        StudentTest.class,
        StudentListTest.class
})
public class AllTests {}


