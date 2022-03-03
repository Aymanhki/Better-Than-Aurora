package com.group_15.bta;

import java.util.ArrayList;

public interface SectionList {

    static SectionListData getInstance() {
        return null;
    }

    ArrayList<Section> getSectionList();

    Section getSection(int position);

    void insertSection(Section currentSection);

    void deleteSection(int position);
}
