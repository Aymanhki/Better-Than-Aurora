package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.util.ArrayList;
/**
 * AccessSections
 * Class to access sections in database.
 */
public class AccessSections implements SectionPersistence {

    //instance of section database
    SectionPersistence sectionPersistence;
    //list of sections in database, used in overlaps method
    public ArrayList<Section> sections = new ArrayList<>();

    //constructor, to start/set database
    public AccessSections() {
        sectionPersistence = Services.getSectionPersistence();
    }

    //constructor, to set database when it has already been started
    public AccessSections(final SectionPersistence sectionPersistence) {
        this();
        this.sectionPersistence = sectionPersistence;
    }

    /**
     * getSectionList
     * returns the sections that are in the database
     * @return an arraylist of the sections in the database
     */
    public ArrayList<Section> getSectionList() {
        return sectionPersistence.getSectionList();
    }

    /**
     * getInstructorSections
     * returns the sections that an instructor teaches that are in the database
     * @return an arraylist of the sections that fall under an instructor in the database
     */
    public ArrayList<Section> getInstructorSections(String name) {
        return sectionPersistence.getInstructorSections(name);
    }

    /**
     * getCourseSections
     * returns the sections that are associated with a course in the database
     * @return an arraylist of the sections that fall under a course in the database
     */
    public ArrayList<Section> getCourseSections(String courseID){
        return sectionPersistence.getCourseSections(courseID);
    }

    /**
     * insertSection
     * inserts a section into the database
     * @param currentSection - section to be inserted
     */
    @Override
    public void insertSection(Section currentSection) {
        sectionPersistence.insertSection(currentSection);
    }

    /**
     * deleteSection
     * deletes a section from the database
     * @param toRemove - section to be deleted
     */
    @Override
    public void deleteSection(Section toRemove) {
        sectionPersistence.deleteSection(toRemove);
    }

    /**
     * getSection
     * given a section id, returns the section that has that particular id in the database
     * @param sectionID - the id we want to see if is in the database, and to be returned if it is
     * @return a section object that has the id given
     */
    @Override
    public Section getSection(String sectionID) {
        return sectionPersistence.getSection(sectionID);
    }

    /**
     * updateSection
     * updates the section variables and the database, given a section
     * @param section - section to be updated
     */
    @Override
    public void updateSection(Section section) {
        sectionPersistence.updateSection(section);
    }

    /**
     * overlaps
     * checks to see if a section will overlap with another section
     * @param potential - section that may potentially overlap
     * @return - true if the section overlaps, false if not
     */
    public boolean overlaps(Section potential)
    {
        boolean toReturn = false;
        //access all sections
        ArrayList<Section> sections = new AccessStudentSections().getSectionList(new AccessUsers().getCurrentUser().getID(), true);

        //check all sections to see if there is an overlap
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
}
