package com.group_15.bta.persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group_15.bta.business.AccessSections;

import com.group_15.bta.objects.Section;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SectionListTest implements SectionPersistence {
    private ArrayList<Section> sections;

    public SectionListTest() {
        this.sections = new ArrayList<>();
        String [] days = new String[] {"M", "F"};
        String [] time = new String[] {"2:00 PM","3:00 PM"};

        sections.add(new Section("A01", "Sara", days, time, "Online", 10, 50, "COMP 4000","Computer Science"));
        sections.add(new Section("A02", days, time, 60));
        sections.add(new Section("A03", days, time, 70));
        sections.add(new Section("A04", days, time, 100));
    }
    @Override
    public ArrayList<Section> getSectionList() {
        return sections;
    }

    @Override
    public ArrayList<Section> getInstructorSections(String instructor) {
        ArrayList<Section> instructorSections = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getInstructor().equals(instructor)) {instructorSections.add(sections.get(i));}
        }
        return instructorSections;
    }

    @Override
    public ArrayList<Section> getCourseSections(String courseID) {
        ArrayList<Section> courseSections = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            if(sections.get(i).getAssociatedCourse()!=null) {
                if (sections.get(i).getAssociatedCourse().equals(courseID)) {
                    courseSections.add(sections.get(i));
                }
            }
            }
        return courseSections;
    }

    @Override
    public void updateSection(Section currentSection) {
        int index;

        index = sections.indexOf(currentSection);
        if (index >= 0) {
            sections.set(index, currentSection);
        }
    }

    @Override
    public void insertSection(Section currentSection) {
        // don't bother checking for duplicates
        sections.add(currentSection);
    }

    @Override
    public void deleteSection(Section currentSection) {
        int index;

        index = sections.indexOf(currentSection);
        if (index >= 0)
        {
            sections.remove(index);
        }
    }
}

