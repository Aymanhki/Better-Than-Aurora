package com.group_15.bta.business;
import android.content.Context;

import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;

import java.util.ArrayList;


public class DataGenerator {
    private static final String ADMINISTRATOR_TEST_USERNAME = "admin";
    private static final String ADMINISTRATOR_TEST_PASSWORD = "admin";
    private static final String STUDENT_TEST_USERNAME = "student";
    private static final String STUDENT_TEST_PASSWORD = "student";
    private static final String ADVISOR_TEST_USERNAME = "advisor";
    private static final String ADVISOR_TEST_PASSWORD = "advisor";
    private static final String INSTRUCTOR_TEST_USERNAME = "instructor";
    private static final String INSTRUCTOR_TEST_PASSWORD = "instructor";

    public static ArrayList<User> createUsers() {
        ArrayList<User> toReturn = new ArrayList<>();
        toReturn.add(new Administrator(ADMINISTRATOR_TEST_USERNAME, ADMINISTRATOR_TEST_PASSWORD));
        toReturn.add(new Student(STUDENT_TEST_USERNAME, STUDENT_TEST_PASSWORD));
        toReturn.add(new Advisor(ADVISOR_TEST_USERNAME, ADVISOR_TEST_PASSWORD));
        toReturn.add(new Instructor(INSTRUCTOR_TEST_USERNAME, INSTRUCTOR_TEST_PASSWORD));
        //Ideally this would read data from a database, create users based on the data and return them.
        return toReturn;
    }

    //Before the last local merge this function read the course catalogue.csv file and created
    //categories, courses, and random sections user smaller function.
    //I have no clue why it all disappeared.
    //This happened in many other classes. There is no time to implement it again now
    //but this is just a placeholder for the data class to call it.
    //THIS WAS A REALLY IMPORTANT FUNCTION :(
    public static ArrayList<Category> createCategories(Context context)
    {
        ArrayList<Category> toReturn = new ArrayList<>();

        return toReturn;
    }
}
