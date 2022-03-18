package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.util.ArrayList;

public class AccessSections implements SectionPersistence {
   // private static final AccessSections ourInstance = new AccessSections();
    SectionPersistence sectionPersistence;
    public ArrayList<Section> sections = new ArrayList<>();

    public AccessSections() {
        sectionPersistence = Services.getSectionPersistence();
    }

    //public static AccessSections getInstance(){return ourInstance;}

    public ArrayList<Section> getSectionList() {
        return sectionPersistence.getSectionList();
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
    public void updateSection(Section section) {
        sectionPersistence.updateSection(section);
    }

}
