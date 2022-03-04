package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;

import java.util.ArrayList;

public interface ISectionList {

    static SectionListData getInstance() {
        return null;
    }

    ArrayList<Section> getSectionList();

    Section getSection(int position);

    void insertSection(Section currentSection);

    void deleteSection(int position);
}
