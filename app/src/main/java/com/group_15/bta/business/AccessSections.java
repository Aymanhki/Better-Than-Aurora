package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.util.ArrayList;

public class AccessSections implements SectionPersistence {
    private static final AccessSections ourInstance = new AccessSections();
    private SectionPersistence sectionPersistence;
    public ArrayList<Section> sections = new ArrayList<>();

    private AccessSections() {
//        String[] days = {"M", "W", "F"};
//        String[] time = {"10:45am", "11:45am"};
//
//        Section s = new Section("A01", days, time, 66);
//        sections.add(s);
//        days= new String[]{"T", "TR"};
//        time= new String[]{"11:00am", "12:00pm"};
//
//        s = new Section("A02",days,time,66);
//        sections.add(s);

        sectionPersistence = Services.getSectionPersistence();
    }

    public static AccessSections getInstance(){
        return ourInstance;
    }

    public ArrayList<Section> getSectionList() {
        return sectionPersistence.getSectionList();
    }

    public void insertSection(Section currentSection) {
        sectionPersistence.insertSection(currentSection);
    }

    public void deleteSection(Section toRemove) {
        sectionPersistence.deleteSection(toRemove);
    }

    @Override
    public void updateSection(Section section) {
        sectionPersistence.updateSection(section);
    }

}
