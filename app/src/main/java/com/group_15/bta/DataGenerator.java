package com.group_15.bta;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.Administrator;
import com.group_15.bta.persistence.Advisor;
import com.group_15.bta.persistence.Instructor;
import com.group_15.bta.persistence.User;

import java.util.ArrayList;


public class DataGenerator
{
    private final String ADMINISTRATOR_TEST_USERNAME = "admin";
    private final String ADMINISTRATOR_TEST_PASSWORD = "admin";
    private final String STUDENT_TEST_USERNAME = "student";
    private final String STUDENT_TEST_PASSWORD = "student";
    private final String ADVISOR_TEST_USERNAME = "advisor";
    private final String ADVISOR_TEST_PASSWORD = "advisor";
    private final String INSTRUCTOR_TEST_USERNAME = "advisor";
    private final String INSTRUCTOR_TEST_PASSWORD = "advisor";
    public ArrayList<User> createUsers()
    {
        ArrayList<User> toReturn = new ArrayList<>();
        toReturn.add(new Administrator(ADMINISTRATOR_TEST_USERNAME,ADMINISTRATOR_TEST_PASSWORD));
        toReturn.add(new Student(STUDENT_TEST_USERNAME,STUDENT_TEST_PASSWORD));
        toReturn.add(new Advisor(ADVISOR_TEST_USERNAME,ADVISOR_TEST_PASSWORD));
        toReturn.add(new Instructor(INSTRUCTOR_TEST_USERNAME,INSTRUCTOR_TEST_PASSWORD));
        //Ideally this would read data from a database, create users based on the data and return them.
        return toReturn;
    }
}
