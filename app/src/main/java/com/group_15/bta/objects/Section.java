package com.group_15.bta.objects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * Class for Section object
 * used to store the section name, as well as other details for a section (days, time, cap)
 */
public class Section implements Serializable {
    private String section;
    private String[] Days;
    private String[] Time;
    private String instructor = "TBA";
    private String location = "TBA";

    private int available;
    private int CAP;
    private String associatedCourse;
    private String associatedCategory;



    enum ClassDays{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        NotValid
    };

    private ClassDays[] classDays;
    //constructor
    public Section(String section, String[] days, String[] time, int CAP) {
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

    public Section(String sectionID, String instructor, String[] days, String[] times,
                   String location, int available, int capacity,
                   String associatedCourse, String associatedCategory) {
        section = sectionID;
        this.instructor = instructor;
        Days = days;
        Time = times;
        this.location = location;
        this.available = available;
        this.CAP = capacity;
        this.associatedCourse = associatedCourse;
        this.associatedCategory = associatedCategory;
        classDays = new ClassDays[days.length];

        for (int i = 0; i <days.length; i++){
            if(days[i].replaceAll("\\s+","").equals("M") || days[i].replaceAll("\\s+","").equals("Monday"))
            {
                classDays[i] = ClassDays.Monday;
            }
            else if(days[i].replaceAll("\\s+","").equals("T") || days[i].replaceAll("\\s+","").equals("Tuesday"))
            {
                classDays[i] = ClassDays.Tuesday;
            }
            else if(days[i].replaceAll("\\s+","").equals("W") || days[i].replaceAll("\\s+","").equals("Wednesday"))
            {
                classDays[i] = ClassDays.Wednesday;
            }
            else if(days[i].replaceAll("\\s+","").equals("TR") || days[i].replaceAll("\\s+","").equals("Thursday"))
            {
                classDays[i] = ClassDays.Thursday;
            }
            else if(days[i].replaceAll("\\s+","").equals("F") || days[i].replaceAll("\\s+","").equals("Friday"))
            {
                classDays[i] = ClassDays.Friday;
            }
            else
            {
                classDays[i] = ClassDays.NotValid;
            }
        }
    }

    //getters
    public String getSection() {
        return section;
    }



    public String getDays() {
        String ret = classDays[0].toString();

        for(int i = 1; i< classDays.length;i++){
            ret = ret + ", " + classDays[i].toString();
        }
        return ret;
    }

    public String getCap(){
        return String.valueOf(CAP);
    }

    public String[] getTimes()
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
        String[] toCompare = potential.Time;

        for (int i = 0; i < toCompare.length && !toReturn; i++) {
            if (i < Time.length) {
                Scanner scanner;
                String potentialTime = toCompare[i];
                String currentTime = Time[i];
                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");

                try {
                    scanner = new Scanner(potentialTime);
                    scanner.useDelimiter("-");
                    String potentialTimeStart = scanner.next().trim();
                    String potentialTimeEnd = scanner.next().trim();
                    Date startTimeA = dateFormat.parse(potentialTimeStart);
                    Date endTimeA = dateFormat.parse(potentialTimeEnd);
                    scanner = new Scanner(currentTime);
                    scanner.useDelimiter("-");
                    String otherTimeStart = scanner.next().trim();
                    String otherTimeEnd = scanner.next().trim();
                    Date startTimeB = dateFormat.parse(otherTimeStart);
                    Date endTimeB = dateFormat.parse(otherTimeEnd);
                    toReturn  = startTimeA.getTime() <= endTimeB.getTime() && startTimeB.getTime() <= endTimeA.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return toReturn;
    }
}