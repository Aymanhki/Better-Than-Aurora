package com.group_15.bta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateStudentTest.class,
        DeleteStudentTest.class,
        EditStudentTest.class,
        SearchStudentAdvisorTest.class,
        UpdateGradeInstructorTest.class,

})
public class AllTests {}

