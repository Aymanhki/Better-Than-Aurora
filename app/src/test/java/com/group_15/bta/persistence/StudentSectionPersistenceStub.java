package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class StudentSectionPersistenceStub implements StudentSectionPersistence {
    private ArrayList<StudentSection> studentSections;

    public StudentSectionPersistenceStub() {
        this.studentSections = new ArrayList<>();
        Section comp3350 = new Section("COMP 3350 - A01", "Dr. Heather Matheson", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 10, 500, "COMP 3350", "Computer Science");
        Section biol1300 = new Section("BIOL 1300 - A01", "Micolash", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 10, 200, "BIOL 1300", "Biological Sciences");
        Section math1500 = new Section("MATH 1500 - A01", "Eilo", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM"}, "Remote", 5, 200, "MATH 1500", "Mathematics");
        Section math1500Lab = new Section("MATH 1500 - B04", "Wallar", new String[]{"Tuesday"}, new String[]{"2:30 PM - 3:45 PM"}, "Remote", 2, 40, "MATH 1500", "Mathematics");
        Section engl1400 = new Section("ENGL 1400 - A04", "Galhad", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 3, 40, "ENGL 1400", "English");
        Section chem1110 = new Section("CHEM 1110 - A02", "Gehrman", new String[]{"Tuesday", " Thursday"}, new String[]{"8:30 AM - 9:45 AM", " 8:30 AM - 9:45 AM"}, "Remote", 3, 221, "CHEM 1110", "Chemistry");
        Section math1300 = new Section("MATH 1300 - A02", "Wallar", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 5, 175, "MATH 1300", "Mathematics");
        Section comp1012 = new Section("COMP 1012 - B01", "Siegward", new String[]{"Wednesday"}, new String[]{"8:30 AM - 9:20 AM"}, "Remote", 2, 16, "COMP 1012", "Computer Science");
        Section biol1010 = new Section("BIOL 1010 - A01", "Gascoigne", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 10, 198, "BIOL 1010", "Biological Sciences");
        studentSections.add(new StudentSection("student", "In Progress", comp3350));
        studentSections.add(new StudentSection("student", "In Progress", biol1300));
        studentSections.add(new StudentSection("student", "In Progress", math1500));
        studentSections.add(new StudentSection("student", "In Progress", math1500Lab));
        studentSections.add(new StudentSection("student", "In Progress", engl1400));
        studentSections.add(new StudentSection("student", "A+", chem1110));
        studentSections.add(new StudentSection("student", "C+", math1300));
        studentSections.add(new StudentSection("student", "C", comp1012));
        studentSections.add(new StudentSection("student", "B", biol1010));
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