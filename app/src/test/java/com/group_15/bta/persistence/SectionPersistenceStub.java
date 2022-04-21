package com.group_15.bta.persistence;

import com.group_15.bta.objects.Section;

import java.util.ArrayList;

public class SectionPersistenceStub implements SectionPersistence {
    private ArrayList<Section> sections;

    public SectionPersistenceStub() {
        this.sections = new ArrayList<>();
        sections.add(new Section("BIOL 1000 - A01", "Yoel", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 10, 238, "BIOL 1000", "Biological Sciences"));
        sections.add(new Section("BIOL 1010 - A01", "Gascoigne", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 10, 198, "BIOL 1010", "Biological Sciences"));
        sections.add(new Section("BIOL 1300 - A01", "Micolash", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.noLongerEarlyBird, "Remote", 10, 200, "BIOL 1300", "Biological Sciences"));
        sections.add(new Section("CHEM 1100 - A01", "Gehrman", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 3, 221, "CHEM 1100", "Chemistry"));
        sections.add(new Section("CHEM 1100 - A02", "Jozef", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.afternoonBirdWithLongCommute, "Remote", 3, 199, "CHEM 1100", "Chemistry"));
        sections.add(new Section("CHEM 1110 - A01", "Jozef", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 3, 221, "CHEM 1110", "Chemistry"));
        sections.add(new Section("CHEM 1110 - A02", "Gehrman", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.earlyBirdWithCoffee, "Remote", 3, 221, "CHEM 1110", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B01", "Brador", new Section.availableSectionDays[]{Section.availableSectionDays.Monday}, Section.availableSectionTimes.almostOwl, "Remote", 1, 40, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B02", "Brador", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.longDayEarlyBird, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B03", "Brador", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.almostOwl, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B04", "Brador", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.longDayEarlyBird, "Remote", 1, 120, "CHEM 1120", "Chemistry"));
        sections.add(new Section("CHEM 1120 - B05", "Brador", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.almostOwl, "Remote", 2, 80, "CHEM 1120", "Chemistry"));
        sections.add(new Section("COMP 1010 - A01", "Vilhelm", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.longDayAfternoonBird, "Remote", 10, 110, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - A02", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 10, 110, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B01", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B02", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B03", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.shortLikesSleepingAfternoonBird, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B04", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.likesSleepingAfternoonBird, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B05", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B06", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B07", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.shortLikesSleepingAfternoonBird, "Remote", 3, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1010 - B08", "Yhorm", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingAfternoonBird, "Remote", 2, 28, "COMP 1010", "Computer Science"));
        sections.add(new Section("COMP 1012 - A01", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 10, 150, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - A02", "Heysel", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 10, 175, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B01", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.earlyBird, "Remote", 2, 16, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B010", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 1, 11, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B011", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 1, 15, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B012", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 1, 8, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B013", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.shortLikesSleepingAfternoonBird, "Remote", 1, 10, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B02", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.perfectEarlyBird, "Remote", 2, 25, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B03", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 2, 17, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B04", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.longCommuteEarlyBird, "Remote", 2, 20, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B05", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.longCommuteEarlyBird, "Remote", 2, 15, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B06", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 2, 24, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B07", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 2, 21, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B08", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.perfectEarlyBird, "Remote", 1, 18, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 1012 - B09", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 1, 22, "COMP 1012", "Computer Science"));
        sections.add(new Section("COMP 3350 - A01", "Dr. Heather Matheson", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.perfectEarlyBird, "Remote", 10, 500, "COMP 3350", "Computer Science"));
        sections.add(new Section("COMP 3350 - A02", "Sara", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.barelyEarlyBird, "Remote", 10, 500, "COMP 3350", "Computer Science"));
        sections.add(new Section("ENGL 1200 - A01", "Eugenius", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.earlyBird, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1200 - A02", "Antal", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.perfectEarlyBird, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1200 - A03", "Djura", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 3, 50, "ENGL 1200", "English"));
        sections.add(new Section("ENGL 1340 - A01", "Antal", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.earlyBirdWithCoffee, "Remote", 3, 40, "ENGL 1340", "English"));
        sections.add(new Section("ENGL 1340 - A02", "Eugenius", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 3, 40, "ENGL 1340", "English"));
        sections.add(new Section("ENGL 1400 - A01", "Djura", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.afternoonBirdWithLongCommute, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A02", "Djura", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.noLongerEarlyBird, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A03", "Galhad", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.perfectEarlyBird, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("ENGL 1400 - A04", "Galhad", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 3, 40, "ENGL 1400", "English"));
        sections.add(new Section("MATH 1300 - A01", "Henryk", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.noLongerEarlyBird, "Remote", 5, 175, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - A02", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday},  Section.availableSectionTimes.perfectEarlyBird, "Remote", 5, 175, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B01", "Henryk", new Section.availableSectionDays[]{Section.availableSectionDays.Monday}, Section.availableSectionTimes.earlyBird, "Remote", 3, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B02", "Henryk", new Section.availableSectionDays[]{Section.availableSectionDays.Monday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 2, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B03", "Henryk", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.afternoonBird, "Remote", 2, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1300 - B04", "Henryk", new Section.availableSectionDays[]{Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingAfternoonBird, "Remote", 3, 40, "MATH 1300", "Mathematics"));
        sections.add(new Section("MATH 1500 - A01", "Eilo", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 5, 200, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - A02", "Iosefka", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.noLongerEarlyBird, "Remote", 5, 200, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B01", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.lateOwl, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B02", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday},Section.availableSectionTimes.earlyBirdWithCoffee, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B03", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.earlyOwl, "Remote", 1, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B04", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B05", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.earlyBirdWithCoffee, "Remote", 2, 40, "MATH 1500", "Mathematics"));
        sections.add(new Section("MATH 1500 - B06", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Thursday}, Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 1, 40, "MATH 1500", "Mathematics"));
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

