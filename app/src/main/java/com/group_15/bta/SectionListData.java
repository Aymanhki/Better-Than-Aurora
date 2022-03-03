package com.group_15.bta;

import java.util.ArrayList;

public class SectionListData implements SectionList{
    private static final SectionListData ourInstance = new SectionListData();
    public ArrayList<Section> sections = new ArrayList<>();
    private SectionListData () {
        String[] days = {"M","W","F"};
        String[] time = {"10:45am", "11:45am"};

        Section s = new Section("A01",days, time, 66);
        sections.add(s);
        days= new String[]{"T", "TR"};
        time= new String[]{"11:00am", "12:00pm"};

        s = new Section("A02",days,time,66);
        sections.add(s);
    }

    public static SectionListData getInstance(){
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
