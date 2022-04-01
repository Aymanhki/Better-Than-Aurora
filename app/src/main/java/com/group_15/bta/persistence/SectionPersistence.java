package com.group_15.bta.persistence;

import com.group_15.bta.business.AccessSections;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public interface SectionPersistence {

    static AccessSections getInstance() {
        return null;
    }
    ArrayList<Section> getSectionList();

    ArrayList<Section> getInstructorSections(final String name);
    ArrayList<Section> getCourseSections(final String courseID);
    void insertSection(Section currentSection);
    void deleteSection(Section toRemove);
    Section getSection(final String sectionID);
    void updateSection(Section section);


}
