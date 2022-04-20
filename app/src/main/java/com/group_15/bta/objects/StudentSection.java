package com.group_15.bta.objects;

import com.group_15.bta.business.AccessCourses;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentSection implements Serializable {

    private final Section section;
    private final String associatedStudent;
    private final Course associatedCourse;
    private final String grade;


    public Section getSection() {
        return section;
    }

    public String getAssociatedStudent() {
        return associatedStudent;
    }

    public String getGrade() {
        return grade;
    }

    public StudentSection(String studentID, String newGrade, Section newSection, Course associatedCourse) {
        associatedStudent = studentID;
        grade = newGrade;
        section = newSection;
        this.associatedCourse = associatedCourse;
    }

    public int getCreditHours() {
        int credits = -1;
        ArrayList<Course> courses = new AccessCourses().getCourseList();
        for (int i = 0; i < courses.size() && credits == -1; i++) {
            if (courses.get(i).getID().equals(section.getAssociatedCourse())) {
                credits = courses.get(i).getCreditHours();
            }
        }

        return credits;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object another) {
        StudentSection temp = (StudentSection) another;
        return associatedStudent.equals(temp.associatedStudent) && grade.equals(temp.grade) && section.getSection().equals(temp.section.getSection());
    }


    public Course getAssociatedCourse() {
        return associatedCourse;
    }
}
