package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.util.ArrayList;

public class AccessStudentSections implements StudentSectionPersistence {
    private StudentSectionPersistence studentSectionPersistence;
    public ArrayList<StudentSection> studentSections = new ArrayList<>();


    public AccessStudentSections() {
        studentSectionPersistence = Services.getStudentSectionPersistence();
    }

    public AccessStudentSections(final StudentSectionPersistence studentSectionPersistence) {
        this();
        this.studentSectionPersistence = studentSectionPersistence;
    }

    public ArrayList<StudentSection> getStudentSectionList() {
        return studentSectionPersistence.getStudentSectionList();
    }

    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID, boolean inProgress) {
        return studentSectionPersistence.getStudentSectionList(studentID, inProgress);
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

    @Override
    public ArrayList<Section> getSectionList(String studentID, boolean inProgress) {
        return studentSectionPersistence.getSectionList(studentID, inProgress);
    }

    @Override
    public ArrayList<Section> getSectionList(String studentID) {
        return studentSectionPersistence.getSectionList(studentID);
    }

    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID) {
        return studentSectionPersistence.getStudentSectionList(studentID);
    }

    @Override
    public ArrayList<Course> getCourses(String studentID) {
        return studentSectionPersistence.getCourses(studentID);
    }

    public boolean duplicate(Student user, Section section){
        return getCourses(user.getID()).contains(new AccessCourses().getCourse(section.getAssociatedCourse()));
    }

    public boolean overlaps(Section potential)
    {
        boolean toReturn = false;
        ArrayList<Section> sections = getSectionList(new AccessUsers().getCurrentUser().getID(), true);

        for(int i=0; i<sections.size() && !toReturn; i++)
        {
            Section currentSection = sections.get(i);
            if(currentSection.interferes(potential))
            {
                toReturn = true;
            }
        }

        return toReturn;
    }

}





