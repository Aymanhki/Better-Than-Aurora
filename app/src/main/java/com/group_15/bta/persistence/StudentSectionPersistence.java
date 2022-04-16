package com.group_15.bta.persistence;


import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public interface StudentSectionPersistence {

    ArrayList<StudentSection> getStudentSectionList();
    ArrayList<StudentSection> getStudentSectionList(String studentID, boolean inProgress);

    void insertSection(StudentSection currentSection);

    ArrayList<StudentSection> getStudentsInSection(final String sectionID);

    void updateStudentSection(StudentSection currentSection);

    void deleteSection(StudentSection toRemove);

    ArrayList<Section> getSectionList(String studentID, boolean inProgress);

    ArrayList<Section> getSectionList(String studentID);

    ArrayList<StudentSection> getStudentSectionList(String studentID);

    ArrayList<Course> getCourses(String studentID);

    }
