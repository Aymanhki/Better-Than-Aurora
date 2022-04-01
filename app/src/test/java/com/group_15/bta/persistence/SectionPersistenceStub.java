package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;

import java.util.ArrayList;

public class SectionPersistenceStub implements SectionPersistence {
    private ArrayList<Section> sections;

    public SectionPersistenceStub() {
        this.sections = new ArrayList<>();
        sections.add(new Section("BIOL 1000 - A01", "Yoel", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 10, 238, "BIOL 1000", "Biological Sciences"));
        sections.add(new Section("BIOL 1010 - A01", "Gascoigne", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 10, 198, "BIOL 1010", "Biological Sciences"));
        sections.add(new Section("BIOL 1300 - A01", "Micolash", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 10, 200, "BIOL 1300", "Biological Sciences"));
        sections.add(new Section("CHEM 1100 - A01", "Gehrman", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"12:30 PM - 1:20 PM", " 12:30 PM - 1:20 PM", " 12:30 PM - 1:20 PM"}, "Remote", 3, 221, "CHEM 1100", "Chemistry"));
        sections.add(new Section("CHEM 1100 - A02", "Jozef", new String[]{"Tuesday", " Thursday"}, new String[]{"1:00 PM - 2:15 PM", " 1:00 PM - 2:15 PM"}, "Remote", 3, 199, "CHEM 1100", "Chemistry"));
        sections.add(new Section("CHEM 1110 - A01", "Jozef", new String[]{"Monday", " Wednesday"}, new String[]{"2:30 PM - 3:45 PM", " 2:30 PM - 3:45 PM"}, "Remote", 3, 221, "CHEM 1110", "Chemistry"));
        sections.add(new Section("CHEM 1110 - A02", "Gehrman", new String[]{"Tuesday", " Thursday"}, new String[]{"8:30 AM - 9:45 AM", " 8:30 AM - 9:45 AM"}, "Remote", 3, 221, "CHEM 1110", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B01", "Brador", new String[]{"Monday"}, new String[]{"2:30 PM - 5:20 PM"}, "Remote", 1, 40, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B02", "Brador", new String[]{"Tuesday"}, new String[]{"8:30 AM - 11:20 AM"}, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B03", "Brador", new String[]{"Tuesday"}, new String[]{"2:30 PM - 5:20 PM"}, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B04", "Brador", new String[]{"Wednesday"}, new String[]{"8:30 AM - 11:20 AM"}, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B05", "Brador", new String[]{"Wednesday"}, new String[]{"2:30 PM - 5:20 PM"}, "Remote", 2, 80, "CHEM 1120", "Chemistry"));
        sections.add(new Section("COMP 1010 - A01", "Vilhelm", new String[]{"Tuesday", " Thrusday"}, new String[]{"12:30 PM - 3:45 PM", " 12:30 PM - 3:45 PM"}, "Remote", 10, 110, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - A02", "Yhorm", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"12:30 PM - 1:20 PM", " 12:30 PM - 1:20 PM", " 12:30 PM - 1:20 PM"}, "Remote", 10, 110, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B01", "Yhorm", new String[]{"Wednesday"}, new String[]{"10:30 AM - 11:20 AM"}, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B02", "Yhorm", new String[]{"Wednesday"}, new String[]{"11:30 AM - 12.20 PM"}, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B03", "Yhorm", new String[]{"Wednesday"}, new String[]{"1:30 PM - 2:20 PM"}, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B04", "Yhorm", new String[]{"Wednesday"}, new String[]{"2:30 PM - 3:20 PM"}, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B05", "Yhorm", new String[]{"Friday"}, new String[]{"10:30 AM - 11:20 AM"}, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B06", "Yhorm", new String[]{"Friday"}, new String[]{"11:30 AM - 12:20 PM"}, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B07", "Yhorm", new String[]{"Friday"}, new String[]{"1:30 PM - 2:20 PM"}, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B08", "Yhorm", new String[]{"Friday"}, new String[]{"2:30 PM - 3:20 PM"}, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1012 - A01", "Siegward", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM"}, "Remote", 10, 150, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - A02", "Heysel", new String[]{"Tuesday", " Thursday"}, new String[]{"2:30 PM - 3:45 PM", " 2:30 PM - 3:45 PM"}, "Remote", 10, 175, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B01", "Siegward", new String[]{"Wednesday"}, new String[]{"8:30 AM - 9:20 AM"}, "Remote", 2, 16, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B010", "Siegward", new String[]{"Friday"}, new String[]{"11:30 AM - 12:20 PM"}, "Remote", 1, 11, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B011", "Siegward", new String[]{"Friday"}, new String[]{"12:30 PM - 1:20 PM"}, "Remote", 1, 15, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B012", "Siegward", new String[]{"Friday"}, new String[]{"12:30 PM - 1:20 PM"}, "Remote", 1, 8, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B013", "Siegward", new String[]{"Friday"}, new String[]{"1:30 PM - 2:20 PM"}, "Remote", 1, 10, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B02", "Siegward", new String[]{"Wednesday"}, new String[]{"9:30 AM - 10:20 AM"}, "Remote", 2, 25, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B03", "Siegward", new String[]{"Wednesday"}, new String[]{"10:30 AM - 11:20 AM"}, "Remote", 2, 17, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B04", "Siegward", new String[]{"Thursday"}, new String[]{"10:00 AM - 10:50 AM"}, "Remote", 2, 20, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B05", "Siegward", new String[]{"Thursday"}, new String[]{"10:00 AM - 10:50 AM"}, "Remote", 2, 15, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B06", "Siegward", new String[]{"Thursday"}, new String[]{"11:30 AM - 12:20 PM"}, "Remote", 2, 24, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B07", "Siegward", new String[]{"Thursday"}, new String[]{"11:30 AM - 12:20 PM"}, "Remote", 2, 21, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B08", "Siegward", new String[]{"Friday"}, new String[]{"9:30 AM - 10:20 AM"}, "Remote", 1, 18, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B09", "Siegward", new String[]{"Friday"}, new String[]{"10:30 AM - 11:20 AM"}, "Remote", 1, 22, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 3350 - A01", "Dr. Heather Matheson", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 10, 500, "COMP 3350", "Computer Science"));
        sections.add(new Section("COMP 3350 - A02", "Sara", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM"}, "Remote", 10, 500, "COMP 3350", "Computer Science"));
        sections.add(new Section("ENGL 1200 - A01", "Eugenius", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"8:30 AM - 9:20 AM", " 8:30 AM - 9:20 AM", " 8:30 AM - 9:20 AM"}, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1200 - A02", "Antal", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1200 - A03", "Djura", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1340 - A01", "Antal", new String[]{"Tuesday", " Thursday"}, new String[]{"8:30 AM - 9:45 AM", " 8:30 AM - 9:45 AM"}, "Remote", 3, 40, "ENGL 1340", "English"));
        sections.add(new Section("ENGL 1340 - A02", "Eugenius", new String[]{"Tuesday", " Thursday"}, new String[]{"2:30 PM - 3:45 PM", " 2:30 PM - 3:45 PM"}, "Remote", 3, 40, "ENGL 1340", "English"));
        sections.add(new Section("ENGL 1400 - A01", "Djura", new String[]{"Tuesday", " Thursday"}, new String[]{"1:00 PM - 2:15 PM", " 1:00 PM - 2:15 PM"}, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A02", "Djura", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A03", "Galhad", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A04", "Galhad", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("MATH 1300 - A01", "Henryk", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 5, 175, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - A02", "Wallar", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 5, 175, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B01", "Henryk", new String[]{"Monday"}, new String[]{"8:30 AM - 9:20 AM"}, "Remote", 3, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B02", "Henryk", new String[]{"Monday"}, new String[]{"10:30 AM - 11:20 AM"}, "Remote", 2, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B03", "Henryk", new String[]{"Friday"}, new String[]{"12:30 PM - 1:20 PM"}, "Remote", 2, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B04", "Henryk", new String[]{"Friday"}, new String[]{"2:30 PM - 3:20 PM"}, "Remote", 3, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1500 - A01", "Eilo", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM"}, "Remote", 5, 200, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - A02", "Iosefka", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 5, 200, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B01", "Wallar", new String[]{"Tuesday"}, new String[]{"7:00 PM - 9:45 PM"}, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B02", "Wallar", new String[]{"Tuesday"}, new String[]{"8:30 AM - 9:45 AM"}, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B03", "Wallar", new String[]{"Tuesday"}, new String[]{"4:00 PM - 5:15 PM"}, "Remote", 1, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B04", "Wallar", new String[]{"Tuesday"}, new String[]{"2:30 PM - 3:45 PM"}, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B05", "Wallar", new String[]{"Thursday"}, new String[]{"8:30 AM - 9:45 AM"}, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B06", "Wallar", new String[]{"Thursday"}, new String[]{"2:30 PM - 3:45 PM"}, "Remote", 1, 40, "MATH 1500", "Mathematics"));
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

    @Override
    public Section getSection(String sectionID) {
        Section toReturn = null;

        for(int i=0; i<sections.size(); i++)
        {
            if(sections.get(i).getSection().equals(sectionID))
            {
                toReturn = sections.get(i);
            }
        }
        return toReturn;
    }
}

