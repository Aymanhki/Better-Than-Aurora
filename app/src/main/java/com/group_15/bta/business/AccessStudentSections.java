package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.util.ArrayList;
/**
 * AccessStudentSections
 * Class to access student sections in database.
 */
public class AccessStudentSections implements StudentSectionPersistence {

    //instance of studentSection database
    private StudentSectionPersistence studentSectionPersistence;
    //list of studentSections in database, used in getStudent and fullTime methods
    public ArrayList<StudentSection> studentSections = new ArrayList<>();

    //constructor, to start/set database
    public AccessStudentSections() {
        studentSectionPersistence = Services.getStudentSectionPersistence();
    }

    //constructor, to set database when it has already been started
    public AccessStudentSections(final StudentSectionPersistence studentSectionPersistence) {
        this();
        this.studentSectionPersistence = studentSectionPersistence;
    }

    /**
     * getStudentSectionList
     * returns the student sections that are in the database
     * @return an arraylist of the student sections in the database
     */
    public ArrayList<StudentSection> getStudentSectionList() {
        return studentSectionPersistence.getStudentSectionList();
    }

    /**
     * getStudentSectionList
     * returns the student sections that are for a particular student and ungraded, in the database
     * @return an arraylist of a students ungraded student sections
     */
    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID, boolean inProgress) {
        return studentSectionPersistence.getStudentSectionList(studentID, inProgress);
    }

    /**
     * getStudentsInSection
     * returns the studentSections associated with a certain section
     * @return an arraylist of the student sections associated with a certain section in the database
     */
    @Override
    public ArrayList<StudentSection> getStudentsInSection(String sectionID) {
        return studentSectionPersistence.getStudentsInSection(sectionID);
    }

    /**
     * insertSection
     * inserts a student section into the database
     * @param currentSection - student section to be inserted
     */
    public void insertSection(StudentSection currentSection) {
        studentSectionPersistence.insertSection(currentSection);
    }

    /**
     * updateStudentSection
     * updates the student section variables and the database, given a student section
     * @param currentSection - student section to be updated
     */
    @Override
    public void updateStudentSection(StudentSection currentSection) {
        studentSectionPersistence.updateStudentSection(currentSection);

    }

    /**
     * deleteSection
     * deletes a student section from the database
     * @param toRemove - student section to be deleted
     */
    public void deleteSection(StudentSection toRemove) {
        studentSectionPersistence.deleteSection(toRemove);
    }

    /**
     * getSectionList
     * returns the sections that are for a particular student and ungraded, in the database
     * @return an arraylist of a students ungraded sections
     */
    @Override
    public ArrayList<Section> getSectionList(String studentID, boolean inProgress) {
        return studentSectionPersistence.getSectionList(studentID, inProgress);
    }

    /**
     * getSectionList
     * returns the sections that are in the database for a particular student
     * @return an arraylist of the sections for one student in the database
     */
    @Override
    public ArrayList<Section> getSectionList(String studentID) {
        return studentSectionPersistence.getSectionList(studentID);
    }

    /**
     * getStudentSectionList
     * returns the student sections that are in the database for a particular student
     * @return an arraylist of the student sections for one student in the database
     */
    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID) {
        return studentSectionPersistence.getStudentSectionList(studentID);
    }

    /**
     * getCourses
     * returns the courses that a student has/is taken/taking that are in the database
     * @return an arraylist of the courses for one student in the database
     */
    @Override
    public ArrayList<Course> getCourses(String studentID) {
        return studentSectionPersistence.getCourses(studentID);
    }

    /**
     * duplicate
     * checks for a duplicate course for a student
     * @param potential - a student section that may be a duplicate
     * @return - true if student section is a duplicate, false if not
     */
    public boolean duplicate(StudentSection potential){
        return getCourses(new AccessUsers().getCurrentUser().getID()).contains(potential.getAssociatedCourse());
    }


}





