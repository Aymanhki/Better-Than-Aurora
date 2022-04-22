package com.group_15.bta.objects;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
/**
 * Class for Section object
 * used to store the section name, as well as other details for a section (days, time, cap)
 */

public class Section implements Serializable {
    private final String section;
    private final availableSectionDays[] Days;
    private final availableSectionTimes Time;
    private String instructor = "TBA";
    private String location = "TBA";
    private int available;
    private final int CAP;
    private String associatedCourse;
    private String associatedCategory;

    public enum availableSectionMinutes {
        zero(0),
        ten(10),
        fifteen(15),
        twenty(20),
        thirty(30),
        fortyFive(45),
        fifty(50);

        private final int minute;

        availableSectionMinutes(int minute)
        {
            this.minute = minute;
        }

        @SuppressLint("DefaultLocale")
        @NonNull
        @Override
        public String toString() {
                return String.format("%02d", minute);
        }
    }
    public enum morningEvening {
        AM,
        PM
    }
    public enum hours {
        one(1),
        two(2),
        three(3),
        four(4),
        five(5),
        six(6),
        seven(7),
        eight(8),
        nine(9),
        ten(10),
        eleven(11),
        twelve(12);

        int hour;

        hours(int hour)
        {
            this.hour = hour;
        }

        @NonNull
        @Override
        public String toString() {
            return  String.valueOf(hour);
        }
    }
    public enum time {
        eightThirtyAm(hours.eight, availableSectionMinutes.thirty, morningEvening.AM),
        nineTwentyAm(hours.nine, availableSectionMinutes.twenty, morningEvening.AM),
        nineThirtyAm(hours.nine, availableSectionMinutes.thirty, morningEvening.AM),
        nineFortyFiveAm(hours.nine, availableSectionMinutes.fortyFive, morningEvening.AM),
        tenAm(hours.ten, availableSectionMinutes.zero, morningEvening.AM),
        tenTwentyAm(hours.ten, availableSectionMinutes.twenty, morningEvening.AM),
        tenThirtyAm(hours.ten, availableSectionMinutes.thirty, morningEvening.AM),
        tenFiftyAm(hours.ten, availableSectionMinutes.fifty, morningEvening.AM),
        elevenTwentyAm(hours.eleven, availableSectionMinutes.twenty, morningEvening.AM),
        elevenThirtyAm(hours.eleven, availableSectionMinutes.thirty, morningEvening.AM),
        twelveTwentyPm(hours.twelve, availableSectionMinutes.twenty, morningEvening.PM),
        twelveThirtyPm(hours.twelve, availableSectionMinutes.thirty, morningEvening.PM),
        twelveFortyFivePm(hours.twelve, availableSectionMinutes.fortyFive, morningEvening.PM),
        onePm(hours.one, availableSectionMinutes.zero, morningEvening.PM),
        oneTwentyPm(hours.one, availableSectionMinutes.twenty, morningEvening.PM),
        oneThirtyPm(hours.one, availableSectionMinutes.thirty, morningEvening.PM),
        twoFifteenPm(hours.two, availableSectionMinutes.fifteen, morningEvening.PM),
        twoTwentyPm(hours.two, availableSectionMinutes.twenty, morningEvening.PM),
        twoThirtyPm(hours.two, availableSectionMinutes.thirty, morningEvening.PM),
        threeTwentyPm(hours.three, availableSectionMinutes.twenty, morningEvening.PM),
        threeFortyFivePm(hours.three, availableSectionMinutes.fortyFive, morningEvening.PM),
        fiveTwentyPm(hours.five, availableSectionMinutes.twenty, morningEvening.PM),
        fourPm(hours.four, availableSectionMinutes.zero, morningEvening.PM),
        fiveFifteenPm(hours.five, availableSectionMinutes.fifteen, morningEvening.PM),
        sevenPm(hours.seven, availableSectionMinutes.zero, morningEvening.PM),
        nineFortyFivePm(hours.nine, availableSectionMinutes.fortyFive, morningEvening.PM);

        hours hour;
        availableSectionMinutes minute;
        morningEvening morningOrEvening;
        time(hours hour, availableSectionMinutes minute, morningEvening morningOrEvening){
            this.hour = hour;
            this.minute = minute;
            this.morningOrEvening = morningOrEvening;
        }

        @NonNull
        @Override
        public String toString()
        {
            return hour.toString()+":"+minute.toString()+" "+morningOrEvening.toString();
        }

    }

    /**
     * Dictionary:
     * 8:30 AM - 9:20 AM earlyBird
     * 8:30 AM - 9:45 AM earlyBirdWithCoffee
     * 8:30 AM - 11:20 AM longDayEarlyBird
     * 9:30 AM - 10:20 AM perfectEarlyBird
     * 10:00 AM - 10:50 AM longCommuteEarlyBird
     * 10:30 AM - 11:20 AM likesSleepingEarlyBird
     * 11:30 AM - 12:20 PM barelyEarlyBird
     * 11:30 AM - 12:45 PM noLongerEarlyBird
     * 12:30 PM - 1:20 PM afternoonBird
     * 12:30 PM - 3:45 PM longDayAfternoonBird
     * 1:00 PM - 2:15 PM afternoonBirdWithLongCommute
     * 1:30 PM - 2:20 PM shortLikesSleepingAfternoonBird
     * 2:30 PM - 3:20 PM likesSleepingAfternoonBird
     * 2:30 PM - 3:45 PM longLikesSleepingAfternoonBird
     * 2:30 PM - 5:20 PM almostOwl
     * 4:00 PM - 5:15 PM earlyOwl
     * 7:00 PM - 9:45 PM lateOwl
     */
    public enum availableSectionTimes  {
        earlyBird(time.eightThirtyAm, time.nineTwentyAm),
        earlyBirdWithCoffee(time.eightThirtyAm, time.nineFortyFiveAm),
        longDayEarlyBird(time.eightThirtyAm, time.elevenTwentyAm),
        perfectEarlyBird(time.nineThirtyAm, time.tenTwentyAm),
        longCommuteEarlyBird(time.tenAm, time.tenFiftyAm),
        likesSleepingEarlyBird(time.tenThirtyAm, time.elevenTwentyAm),
        barelyEarlyBird(time.elevenThirtyAm, time.twelveTwentyPm),
        noLongerEarlyBird(time.elevenThirtyAm, time.twelveFortyFivePm),
        afternoonBird(time.twelveThirtyPm, time.oneTwentyPm),
        longDayAfternoonBird(time.twelveThirtyPm, time.threeFortyFivePm),
        afternoonBirdWithLongCommute(time.onePm, time.twoFifteenPm),
        shortLikesSleepingAfternoonBird(time.oneThirtyPm, time.twoTwentyPm),
        likesSleepingAfternoonBird(time.twoThirtyPm, time.threeTwentyPm),
        longLikesSleepingAfternoonBird(time.twoThirtyPm, time.threeFortyFivePm),
        almostOwl(time.twoThirtyPm, time.fiveTwentyPm),
        earlyOwl(time.fourPm, time.fiveFifteenPm),
        lateOwl(time.sevenPm, time.nineFortyFivePm);

        time startTime;
        time endTime;

        availableSectionTimes(time startTime, time endTime) {
            this.startTime = startTime;
            this.endTime = endTime;

        }

        @NonNull
        @Override
        public String toString()
        {
            return startTime.toString() +" - "+endTime.toString();
        }

        public static availableSectionTimes getEnum(String value) {
            for(availableSectionTimes v : values())
                if(v.toString().equalsIgnoreCase(value)) return v;
            throw new IllegalArgumentException();
        }
    }

    public enum availableSectionDays {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }

    //constructor
    public Section(String section, availableSectionDays[] days, availableSectionTimes time, int CAP) {
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

    public Section(String sectionID, String instructor, availableSectionDays[] days, availableSectionTimes time,
                   String location, int available, int capacity,
                   String associatedCourse, String associatedCategory) {
        section = sectionID;
        this.instructor = instructor;

        Time = time;
        this.location = location;
        this.available = available;
        this.CAP = capacity;
        this.associatedCourse = associatedCourse;
        this.associatedCategory = associatedCategory;
        this.Days = days;
    }


    //getters
    public String getSection() {
        return section;
    }

    public String getDays() {
        StringBuilder ret = new StringBuilder(Days[0].toString());

        for(int i = 1; i< Days.length; i++){
            ret.append(", ").append(Days[i].toString());
        }
        return ret.toString();
    }

    public String getCap(){
        return String.valueOf(CAP);
    }

    public availableSectionTimes getTime()
    {
        return Time;
    }

    public availableSectionDays[] getDaysRaw()
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
            scanner = new Scanner(potential.Time.toString());
            scanner.useDelimiter("-");
            String potentialTimeStart = scanner.next().trim();
            String potentialTimeEnd = scanner.next().trim();
            Date startTimeA = dateFormat.parse(potentialTimeStart);
            Date endTimeA = dateFormat.parse(potentialTimeEnd);
            scanner = new Scanner(Time.toString());
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

    public static String toString(availableSectionDays[] sectionDays) {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < sectionDays.length;  i++) {
            toReturn.append(sectionDays[i].toString());

            if(i < sectionDays.length - 1)
            {
                toReturn.append(", ");
            }
        }

        return toReturn.toString();
    }

    public static availableSectionDays[] toDays(String[] sectionDays) {
        availableSectionDays[] toReturn = new availableSectionDays[sectionDays.length];

        for (int i = 0; i < sectionDays.length;  i++) {
            toReturn[i] = availableSectionDays.valueOf(sectionDays[i].trim());
        }

        return toReturn;
    }

    public boolean availablePosition (){ return available+1 <= CAP;}

}