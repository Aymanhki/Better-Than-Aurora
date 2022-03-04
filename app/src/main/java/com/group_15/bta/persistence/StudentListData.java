package com.group_15.bta.persistence;

import com.group_15.bta.objects.Student;

import java.util.ArrayList;

/*
 * Class for List of students ("database")
 */

public class StudentListData implements IStudentList {

    private static final StudentListData ourInstance = new StudentListData(); //one instance
    public ArrayList<Student> studentList = new ArrayList<>(); //list of students

    private StudentListData () {

        //hardcoded data to add to student list "database"
        Student one = new Student("12", "12", "Jane Doe");
        Student two = new Student("13", "13", "John Doe");
        this.studentList.add(one);
        this.studentList.add(two);
    }

    //getters
    public static StudentListData getInstance(){
        return ourInstance;
    }

    public Student getStudent(int position){
       return studentList.get(position);
    }

    public ArrayList<Student> getStudentList(){
        return this.studentList;
    }

    //add a student to the list
    public void insertStudent(Student currentStudent){
        studentList.add(currentStudent);
    }

    //delete a student from the list
    public void deleteStudent(int position){
        studentList.remove(position);
    }
}
