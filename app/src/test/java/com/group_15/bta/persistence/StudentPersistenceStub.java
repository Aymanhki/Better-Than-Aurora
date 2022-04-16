package com.group_15.bta.persistence;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class StudentPersistenceStub implements StudentPersistence {
    private ArrayList<Student> students;

    public StudentPersistenceStub() {
        this.students = new ArrayList<>();
        students.add(new Student("student", "student", "Ayman", "B.Sc. (Hons)"));
        students.add(new Student("student1", "student1", "Nilin", "B.Sc. (Hons)"));
        students.add(new Student("student2", "student2", "Dara", "B.Sc. (Hons)"));
        students.add(new Student("student3", "student3", "Amelia", "B.Sc. (Hons)"));
    }
    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }

    @Override
    public ArrayList<Student> getStudent(Student student) {
        ArrayList<Student> studentToReturn = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(student.getID())) {studentToReturn.add(students.get(i));}
        }
        return studentToReturn;
    }

    @Override
    public void updateStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0) {
            students.set(index, currentStudent);
        }
    }

    @Override
    public void insertStudent(Student currentStudent) {
        // don't bother checking for duplicates
        students.add(currentStudent);
    }

    @Override
    public void deleteStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            students.remove(index);
        }
    }

    @Override
    public void deleteStudentID(String currentStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(currentStudent)) {students.remove(i);}
        }
    }

    //TODO: Implement this method
    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student) {
        return null;
    }

    //TODO: Implement this method
    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        return null;
    }

    //TODO: Implement this method
    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student) {
        return null;
    }


}
