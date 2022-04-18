package com.group_15.bta;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Student;

public class LoginTests {

    AccessUsers testLogIn;
    Student testStudent = new Student();
    final String USER_SAMPLE = "Stew";
    final String PASS_SAMPLE = "Dent";
    Student testStudent2 = new Student(USER_SAMPLE, PASS_SAMPLE);
    Administrator testAdmin = new Administrator();
    Administrator testAdmin2 = new Administrator(USER_SAMPLE, PASS_SAMPLE);
    final String STUDENT_KEY = "Student";
    final String ADMIN_KEY = "Administrator";

}
