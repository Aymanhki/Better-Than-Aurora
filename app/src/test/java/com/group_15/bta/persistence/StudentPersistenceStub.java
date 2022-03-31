package com.group_15.bta.persistence;

import com.group_15.bta.objects.Student;

import java.util.ArrayList;

public class StudentPersistenceStub implements StudentPersistence {
    private ArrayList<Student> students;

    public StudentPersistenceStub() {
        this.students = new ArrayList<>();

        students.add(new Student("101"));
        students.add(new Student("102"));
        students.add(new Student("303"));
        students.add(new Student("404"));
    }
    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }

    @Override
    public ArrayList<Student> getStudent(Student student) {
        ArrayList<Student> studentToReturn = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(student.getStudentID())) {studentToReturn.add(students.get(i));}
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
}
