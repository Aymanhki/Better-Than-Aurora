package com.group_15.bta.objects;

public class StudentSection {

    private Section section;
    private String associatedStudent;
    private String grade;

    public Section getSection() {
        return section;
    }

    public String getAssociatedStudent() {
        return associatedStudent;
    }

    public String getGrade() {
        return grade;
    }

    public StudentSection(String studentID, String newGrade, Section newSection) {
        associatedStudent = studentID;
        grade = newGrade;
        section = newSection;
    }
}
