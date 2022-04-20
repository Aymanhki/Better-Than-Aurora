package com.group_15.bta.persistence;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public interface StudentPersistence {

    ArrayList<Student> getStudent(Student currentStudent);

    ArrayList<Student> getStudentList();

    void insertStudent(Student currentStudent);

    void updateStudent(Student currentStudent);

    void deleteStudent(Student toRemove);

    void deleteStudentID(String toRemove);

    ArrayList<PieEntry> getDegreeCreditBreakDown(Student student);

    ArrayList<Course> getStudentDegreeNotTakenCourses(Student student);

    StudentSection getEnrolledSection(Student student, Section section);

}
