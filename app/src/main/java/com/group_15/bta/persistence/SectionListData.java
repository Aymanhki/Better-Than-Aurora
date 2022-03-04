package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;

import java.util.ArrayList;

/*
 * Class for List of sections of a course ("database")
 */

public class SectionListData implements ISectionList {

    private static final SectionListData ourInstance = new SectionListData(); //one instance
    public ArrayList<Section> sections = new ArrayList<>(); //list of sections

    //constructor
    private SectionListData () {

        //hardcoded data to add to list of sections "database"
        String[] days = {"M","W","F"};
        String[] time = {"10:45am", "11:45am"};

        Section s = new Section("A01",days, time, 66);
        sections.add(s);
        days= new String[]{"T", "TR"};
        time= new String[]{"11:00am", "12:00pm"};

        s = new Section("A02",days,time,66);
        sections.add(s);
    }

    //getters
    public static SectionListData getInstance(){
        return ourInstance;
    }

    public Section getSection(int position){
        return sections.get(position);
    }

    public ArrayList<Section> getSectionList(){
        return this.sections;
    }

    //add a section to the list
    public void insertSection(Section currentSection){
        sections.add(currentSection);
    }

    //delete a section from the list
    public void deleteSection(int position){
        sections.remove(position);
    }

}
