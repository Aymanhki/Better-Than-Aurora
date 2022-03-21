package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistence;
import com.group_15.bta.application.Services;
import com.group_15.bta.persistence.StudentSectionPersistence;
import java.util.ArrayList;
import java.util.List;

public class AccessStudentSections implements StudentSectionPersistence {
    private static final AccessStudentSections ourInstance = new AccessStudentSections();
    private StudentSectionPersistence studentSectionPersistence;
    public ArrayList<StudentSection> studentSections = new ArrayList<>();
    private ArrayList<StudentSection> studentsInSection = new ArrayList<>();
    private int currentStudentSection;


    public AccessStudentSections() {
        studentSectionPersistence = Services.getStudentSectionPersistence();
    }

    public static AccessStudentSections getInstance() {
        return ourInstance;
    }

    public ArrayList<StudentSection> getSectionList() {
        return studentSectionPersistence.getSectionList();
    }

    @Override
    public ArrayList<StudentSection> getStudentsInSection(String sectionID) {
           return studentSectionPersistence.getStudentsInSection(sectionID);
    }

    public void insertSection(StudentSection currentSection) {
        studentSectionPersistence.insertSection(currentSection);
    }

    @Override
    public void updateStudentSection(StudentSection currentSection) {
        studentSectionPersistence.updateStudentSection(currentSection);

    }

    public void deleteSection(StudentSection toRemove) {
        studentSectionPersistence.deleteSection(toRemove);
    }
}



