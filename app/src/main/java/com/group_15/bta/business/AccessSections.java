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

    public boolean overlaps(Section potential)
    {
        boolean toReturn = false;
        ArrayList<Section> sections = new AccessStudentSections().getSectionList(new AccessUsers().getCurrentUser().getID(), true);

        for(int i=0; i<sections.size() && !toReturn; i++)
        {
            Section currentSection = sections.get(i);
            if(currentSection.interferes(potential))
            {
                toReturn = true;
            }
        }

        return toReturn;
    }

    public boolean validateDays(String days)
    {
        String[] daysSplit = days.split(",");
        boolean valid = true;

        for(int i=0; i<daysSplit.length && valid; i++)
        {
            if(!Section.validDay(daysSplit[i]))
            {
                valid = false;
            }
        }

        return valid;
    }

    public boolean validateDayAndTime(String startTimes, String endTimes, String days)
    {


        String[] startTimesSplit = startTimes.split(",");
        String[] endTimesSplit = endTimes.split(",");
        String[] daysSplit = days.split(",");

        return (startTimesSplit.length == endTimesSplit.length) && validateDays(days);
    }

    public String[] timeParser(String startTimes, String endTimes, String[] days)
    {

        String[] finalTimes = null;
        String[] startTimesSplit = startTimes.split(",");
        String[] endTimesSplit = endTimes.split(",");

        if(startTimesSplit.length == endTimesSplit.length)
        {
            finalTimes = new String[days.length];

            for(int i=0; i<days.length; i++)
            {
                finalTimes[i] = startTimesSplit[0]+" - "+endTimesSplit[0];
            }
        }

        return finalTimes;
    }

}
