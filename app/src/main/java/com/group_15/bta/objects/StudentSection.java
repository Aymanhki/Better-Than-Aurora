package com.group_15.bta.objects;

import android.widget.ArrayAdapter;

import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.business.AccessCourses;

import java.util.ArrayList;

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

    public int getCreditHours() {
        int credits = -1;
        ArrayList<Courses> courses = new AccessCourses().getCourseList();
        for (int i = 0; i < courses.size() && credits == -1; i++) {
            if (courses.get(i).getID().equals(section.getAssociatedCourse())) {
                credits = courses.get(i).getCreditHours();
            }
        }

        return credits;
    }
}
