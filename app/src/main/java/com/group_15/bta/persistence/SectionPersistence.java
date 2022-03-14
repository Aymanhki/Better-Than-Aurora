package com.group_15.bta.persistence;

import com.group_15.bta.business.AccessSections;
import com.group_15.bta.objects.Section;

import java.util.ArrayList;

public interface SectionPersistence {

    static AccessSections getInstance() {
        return null;
    }

    ArrayList<Section> getSectionList();

    void insertSection(Section currentSection);

    void deleteSection(Section toRemove);
}
