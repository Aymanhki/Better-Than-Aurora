package com.group_15.bta.objects;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/*
 * Class for Section object
 * used to store the section name, as well as other details for a section (days, time, cap)
 */
public class Section implements Serializable {
    private final String section;
    private final String[] Days;
    private final String Time;
    private String instructor = "TBA";
    private String location = "TBA";

    private int available;
    private final int CAP;
    private String associatedCourse;
    private String associatedCategory;

    enum ClassDays{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        NotValid
    }

    private ClassDays[] classDays;
    //constructor
    public Section(String section, String[] days, String time, int CAP) {
        this.section = section;
        Days = days;
        Time = time;
        this.CAP = CAP;
    }


    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public String getAssociatedCategory() {
        return associatedCategory;
    }

    public Section(String sectionID, String instructor, String[] days, String time,
                   String location, int available, int capacity,
                   String associatedCourse, String associatedCategory) {
        section = sectionID;
        this.instructor = instructor;
        Days = days;
        Time = time;
        this.location = location;
        this.available = available;
        this.CAP = capacity;
        this.associatedCourse = associatedCourse;
        this.associatedCategory = associatedCategory;
        setClassDays();
    }

    //set days
    private void setClassDays(){
        classDays = new ClassDays[Days.length];

        for (int i = 0; i <Days.length; i++){
            if(Days[i].replaceAll("\\s+","").equals("M") || Days[i].replaceAll("\\s+","").equals("Monday"))
            {
                classDays[i] = ClassDays.Monday;
                Days[i] = ClassDays.Monday.toString();
            }
            else if(Days[i].replaceAll("\\s+","").equals("T") || Days[i].replaceAll("\\s+","").equals("Tuesday"))
            {
                classDays[i] = ClassDays.Tuesday;
                Days[i] = ClassDays.Tuesday.toString();
            }
            else if(Days[i].replaceAll("\\s+","").equals("W") || Days[i].replaceAll("\\s+","").equals("Wednesday"))
            {
                classDays[i] = ClassDays.Wednesday;
                Days[i] = ClassDays.Wednesday.toString();
            }
            else if(Days[i].replaceAll("\\s+","").equals("TR") || Days[i].replaceAll("\\s+","").equals("Thursday"))
            {
                classDays[i] = ClassDays.Thursday;
                Days[i] = ClassDays.Thursday.toString();
            }
            else if(Days[i].replaceAll("\\s+","").equals("F") || Days[i].replaceAll("\\s+","").equals("Friday"))
            {
                classDays[i] = ClassDays.Friday;
                Days[i] = ClassDays.Friday.toString();
            }
            else
            {
                classDays[i] = ClassDays.NotValid;
                Days[i] = ClassDays.NotValid.toString();
            }
        }
    }

    //getters
    public String getSection() {
        return section;
    }

    public String getDays() {
        StringBuilder ret = new StringBuilder(classDays[0].toString());

        for(int i = 1; i< classDays.length;i++){
            ret.append(", ").append(classDays[i].toString());
        }
        return ret.toString();
    }

    public String getCap(){
        return String.valueOf(CAP);
    }

    public String getTime()
    {
        return Time;
    }

    public String[] getDaysRaw()
    {
        return Days;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailable() {
        return available;
    }

    public int getCAP() {
        return CAP;
    }

    public String getInstructor() {
        return instructor;
    }


    public boolean interferes(Section potential) {
        boolean toReturn = false;
        Scanner scanner;
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");

        try {
            scanner = new Scanner(potential.Time);
            scanner.useDelimiter("-");
            String potentialTimeStart = scanner.next().trim();
            String potentialTimeEnd = scanner.next().trim();
            Date startTimeA = dateFormat.parse(potentialTimeStart);
            Date endTimeA = dateFormat.parse(potentialTimeEnd);
            scanner = new Scanner(Time);
            scanner.useDelimiter("-");
            String otherTimeStart = scanner.next().trim();
            String otherTimeEnd = scanner.next().trim();
            Date startTimeB = dateFormat.parse(otherTimeStart);
            Date endTimeB = dateFormat.parse(otherTimeEnd);
            assert startTimeA != null;
            assert endTimeB != null;
            toReturn  = startTimeA.getTime() <= endTimeB.getTime() && Objects.requireNonNull(startTimeB).getTime() <= Objects.requireNonNull(endTimeA).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return toReturn;
    }


}