package com.group_15.bta.objects;

import com.group_15.bta.business.AccessCourses;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class for StudentSection object
 * used to store the information for a student for a particular section they are enrolled in
 */
public class StudentSection implements Serializable {

    private final Section section;
    private final String associatedStudent;
    private final Course associatedCourse;
    private final String grade;

    //constructor
    public StudentSection(String studentID, String newGrade, Section newSection, Course associatedCourse) {
        associatedStudent = studentID;
        grade = newGrade;
        section = newSection;
        this.associatedCourse = associatedCourse;
    }

    //getters
    public Section getSection() {
        return section;
    }

    public String getAssociatedStudent() {
        return associatedStudent;
    }

    public String getGrade() {
        return grade;
    }

    public Course getAssociatedCourse() {
        return associatedCourse;
    }

    //returns credit hours for the course linked to this student section
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

}
