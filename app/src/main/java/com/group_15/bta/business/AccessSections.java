package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.CoursePersistence;
import com.group_15.bta.persistence.SectionPersistence;

import java.util.ArrayList;

public class AccessSections implements SectionPersistence {
   // private static final AccessSections ourInstance = new AccessSections();
    SectionPersistence sectionPersistence;
    public ArrayList<Section> sections = new ArrayList<>();

    public AccessSections() {
        sectionPersistence = Services.getSectionPersistence();
    }

    public AccessSections(final SectionPersistence sectionPersistence) {
        this();
        this.sectionPersistence = sectionPersistence;
    }
    //public static AccessSections getInstance(){return ourInstance;}

    public ArrayList<Section> getSectionList() {
        return sectionPersistence.getSectionList();
    }

    public ArrayList<Section> getInstructorSections(String name) {
        return sectionPersistence.getInstructorSections(name);
    }

    public ArrayList<Section> getCourseSections(String courseID){
        return sectionPersistence.getCourseSections(courseID);
    }

    @Override
    public void insertSection(Section currentSection) {
        sectionPersistence.insertSection(currentSection);
    }

    @Override
    public void deleteSection(Section toRemove) {
        sectionPersistence.deleteSection(toRemove);
    }

    @Override
    public Section getSection(String sectionID) {
        return sectionPersistence.getSection(sectionID);
    }

    @Override
    public void updateSection(Section section) {
        sectionPersistence.updateSection(section);
    }

}
