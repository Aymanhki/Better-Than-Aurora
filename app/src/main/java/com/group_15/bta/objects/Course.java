package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for Courses object
 * used to store the course id, description, and sections that are available for this course
 */
public class Course implements Serializable {

    private String ID; //course id
    private String Description;//course description
    protected ArrayList<Section> sections; //sections for this course
    private String title;
    private String associatedCategory;
    private int creditHours;
    //constructor
    public Course(String ID, String title) {
        this.title = title;
        this.ID = ID;
    }

    public Course(String courseID, String courseTitle, String courseDescription, int credit, String associatedCategory) {
        sections = new ArrayList<>();
        ID = courseID;
        title = courseTitle;
        Description = courseDescription;
        this.associatedCategory = associatedCategory;
        creditHours = credit;
    }

    //constructor
    public Course(String ID, String title, String Description, ArrayList<Section> sections) {
        this.ID = ID;
        this.title = title;
        this.Description = Description;
        this.sections = sections;
    }

    //adds section to this course
    public void addSection(String sectionNumber, String[] Days, String[] Time, int CAP) {
        Section S = new Section(sectionNumber, Days, Time, CAP);
        sections.add(S);
    }

    public void addSection(Section newSection) {
        sections.add(newSection);
    }

    //getters
    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public String getID() {
        return this.ID;
    }

    public String getAssociatedCategory() {
        return associatedCategory;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public boolean equals(final Course o)
    {
        return Objects.equals(this.ID, o.ID) &&
                Objects.equals(this.Description, o.Description) &&
                Objects.equals(this.sections, o.sections) &&
                Objects.equals(this.title, o.title) &&
                Objects.equals(this.associatedCategory, o.associatedCategory) &&
                Objects.equals(this.creditHours, o.creditHours);

    }
}