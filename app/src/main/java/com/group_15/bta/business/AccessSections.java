package com.group_15.bta.business;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.util.ArrayList;

public class AccessSections implements SectionPersistence {
    private static final AccessSections ourInstance = new AccessSections();

    public ArrayList<Section> sections = new ArrayList<>();

    private AccessSections() {
        String[] days = {"M", "W", "F"};
        String[] time = {"10:45am", "11:45am"};

        Section s = new Section("A01", days, time, 66);
        sections.add(s);
        days= new String[]{"T", "TR"};
        time= new String[]{"11:00am", "12:00pm"};

        s = new Section("A02",days,time,66);
        sections.add(s);
    }

    public static AccessSections getInstance(){
        return ourInstance;
    }

    public Section getSection(int position){
        return sections.get(position);
    }

    public ArrayList<Section> getSectionList(){
        return this.sections;
    }

    public void insertSection(Section currentSection){
        sections.add(currentSection);
    }

    public void deleteSection(int position){
        sections.remove(position);
    }

}
