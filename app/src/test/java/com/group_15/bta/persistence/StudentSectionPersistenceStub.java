package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class StudentSectionPersistenceStub implements StudentSectionPersistence {
    private ArrayList<StudentSection> studentSections;

    public StudentSectionPersistenceStub() {
        this.studentSections = new ArrayList<>();
        String [] days = new String[] {"M", "F"};
        String [] time = new String[] {"2:00 PM","3:00 PM"};

        Section section = new Section("A01", "Sara", days, time, "Online", 10, 50, "COMP 4000","Computer Science");

        studentSections.add(new StudentSection("101", "A", section));
        studentSections.add(new StudentSection("102","B", section));
        studentSections.add(new StudentSection("303","C", section));
        studentSections.add(new StudentSection("404", "D", section));
    }
    @Override
    public ArrayList<StudentSection> getSectionList() {
        return studentSections;
    }

    @Override
    public ArrayList<StudentSection> getStudentsInSection(String section) {
        ArrayList<StudentSection> studentToReturn = new ArrayList<>();
        for (int i = 0; i < studentSections.size(); i++) {
            if (studentSections.get(i).getSection().getSection().equals(section)) {studentToReturn.add(studentSections.get(i));}
        }
        return studentToReturn;
    }

    @Override
    public void updateStudentSection(StudentSection currentStudentSection) {
        int index;

        index = studentSections.indexOf(currentStudentSection);
        if (index >= 0) {
            studentSections.set(index, currentStudentSection);
        }
    }

    @Override
    public void insertSection(StudentSection currentStudentSection) {
        // don't bother checking for duplicates
        studentSections.add(currentStudentSection);
    }

    @Override
    public void deleteSection(StudentSection currentStudentSection) {
        int index;

        index = studentSections.indexOf(currentStudentSection);
        if (index >= 0)
        {
            studentSections.remove(index);
        }
    }
}