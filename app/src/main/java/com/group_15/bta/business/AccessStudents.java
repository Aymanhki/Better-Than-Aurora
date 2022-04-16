package com.group_15.bta.business;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.application.Services;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.util.ArrayList;
import java.util.Scanner;

public class AccessStudents implements StudentPersistence {

    private static final AccessStudents ourInstance = new AccessStudents();
    public ArrayList<Student> studentList = new ArrayList<>();
    private StudentPersistence studentPersistence;



    private StudentSectionPersistence studentSectionPersistence = Services.getStudentSectionPersistence();
    private Student student;

    public AccessStudents() {
        studentPersistence = Services.getStudentPersistence();
    }

    public static AccessStudents getInstance() {
        return ourInstance;
    }

    public AccessStudents(final StudentPersistence studentPersistence) {
        this();
        this.studentPersistence = studentPersistence;
    }

    public ArrayList<Student> getStudent (Student getStudent) {
        student = null;
        ArrayList<Student> studentToReturn = new ArrayList<>();
        studentList = studentPersistence.getStudent(new Student(getStudent.getID()));
        if (studentList.size()==1)
        {
            student = studentList.get(0);
            studentToReturn.add(student);
        }
        return studentToReturn;
    }

    public ArrayList<Student> getStudentList() {
        return studentPersistence.getStudentList();
    }

    @Override
    public void updateStudent(Student currentStudent) {
        studentPersistence.updateStudent(currentStudent);
    }

    public void insertStudent(Student currentStudent) {
        studentPersistence.insertStudent(currentStudent);
    }

    public void deleteStudent(Student toRemove) {
        studentPersistence.deleteStudent(toRemove);
    }

    public void deleteStudentID(String toRemove) { studentPersistence.deleteStudentID(toRemove); }



    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        return studentPersistence.getEnrolledSection(student, section);
    }

    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student) {
        return studentPersistence.getStudentDegreeNotTakenCourses(student);
    }

    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student) {
        return studentPersistence.getDegreeCreditBreakDown(student);
    }


    public boolean fullTime()
    {
        int counter = 0;
        Student student = (Student) new AccessUsers().getCurrentUser();
        ArrayList<StudentSection> studentSections = student.getEnrolledSections();

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

        return counter == student.getMAX_CLASSES();
    }


}
