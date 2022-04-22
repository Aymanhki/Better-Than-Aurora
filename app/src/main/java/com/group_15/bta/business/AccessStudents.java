package com.group_15.bta.business;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentPersistence;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * AccessStudents
 * Class to access students in database.
 */
public class AccessStudents implements StudentPersistence {

    //instance of student database
    private StudentPersistence studentPersistence;

    //constructor, to start/set database
    public AccessStudents() {
        studentPersistence = Services.getStudentPersistence();
    }

    //constructor, to set database when it has already been started
    public AccessStudents(final StudentPersistence studentPersistence) {
        this();
        this.studentPersistence = studentPersistence;
    }

    /**
     * getStudent
     * returns a student that matches the student given in the database
     * @return an arraylist consisting of the student that matches
     */
    public ArrayList<Student> getStudent (Student getStudent) {
        Student student;
        ArrayList<Student> studentToReturn = new ArrayList<>();
        ArrayList<Student> studentList = studentPersistence.getStudent(new Student(getStudent.getID()));
        if (studentList.size()==1)
        {
            student = studentList.get(0);
            studentToReturn.add(student);
        }
        return studentToReturn;
    }


    /**
     * getStudentList
     * returns the students that are in the database
     * @return an arraylist of the students in the database
     */
    public ArrayList<Student> getStudentList() {
        return studentPersistence.getStudentList();
    }

    /**
     * updateStudent
     * updates the student variables and the database, given a student
     * @param currentStudent - student to be updated
     */
    @Override
    public void updateStudent(Student currentStudent) {
        studentPersistence.updateStudent(currentStudent);
    }

    /**
     * insertStudent
     * inserts a student into the database
     * @param currentStudent - student to be inserted
     */
    public void insertStudent(Student currentStudent) {
        studentPersistence.insertStudent(currentStudent);
    }

    /**
     * deleteStudent
     * deletes a student from the database
     * @param toRemove - student to be deleted
     */
    public void deleteStudent(Student toRemove) {
        studentPersistence.deleteStudent(toRemove);
    }

    /**
     * deleteStudentID
     * deletes a student with a particular id from the database
     * @param toRemove - string of student id that we want to remove
     */
    public void deleteStudentID(String toRemove) { studentPersistence.deleteStudentID(toRemove); }


    /**
     * getEnrolledSection
     * gets the section a student is enrolled in
     * @param student - student we want to get the enrolled section of
     * @param section - associated section of the enrolled section we want
     * @return - student section object that is associated with both the params
     */
    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        return studentPersistence.getEnrolledSection(student, section);
    }

    /**
     * getStudentDegreeNotTakenCourses
     * gets the courses a student still needs to complete to get a degree
     * @param student - the student we want to check what courses they still need
     * @return - an arraylist of courses, containing the courses a student still needs to take
     */
    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student) {
        return studentPersistence.getStudentDegreeNotTakenCourses(student);
    }

    /**
     * getDegreeCreditBreakDown
     * returns the pie chart values, representing a students progress on a degree
     * @param student - the student we want the degree breakdown for
     * @return - an arraylist of pie entries for the student
     */
    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student) {
        return studentPersistence.getDegreeCreditBreakDown(student);
    }


    /**
     * fullTime
     * checks if a student is a full time student
     * @param student - The student being checked if they were full time
     * @return - true if a student is a full time student, false if not
     */
    public boolean fullTime(Student student)
    {
        int counter = 0;
        //get the sections
        ArrayList<StudentSection> studentSections = student.getEnrolledSections();

        //go through student sections and check if they are full time
        for(int i=0; i<studentSections.size(); i++)
        {
            Scanner scanner = new Scanner(studentSections.get(i).getSection().getSection());
            scanner.useDelimiter("-");
            String sectionID = scanner.next().trim();

            if(!sectionID.contains("B0"))
            {
                counter++;
            }
        }

        return counter >= student.getMAX_CLASSES();
    }


}
