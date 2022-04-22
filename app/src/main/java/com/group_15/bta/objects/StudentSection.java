package com.group_15.bta.objects;
import androidx.annotation.NonNull;
import java.io.Serializable;
/**
 * Class for StudentSection object
 * used to store the information for a student for a particular section they are enrolled in
 */
public class StudentSection implements Serializable {

    private final Section section;
    private final String associatedStudent;
    private final Course associatedCourse;
    private final grades grade;

    public enum grades  {
        APlus("A+"),
        A("A"),
        BPlus("B+"),
        B("B"),
        CPlus("C+"),
        C("C"),
        D("D"),
        F("F"),
        IP("In Progress");

        String grade;
        grades(String grade)
        {
            this.grade = grade;
        }

        @NonNull
        @Override
        public String toString() {
            return  grade;
        }

        public static grades getEnum(String value) {
            for(grades v : values())
                if(v.toString().equals(value)) return v;
            throw new IllegalArgumentException();
        }

        public boolean equals(grades another){ return grade.equals(another.grade);}
    }

    public enum gpa {
        fourPointFive(4.5),
        four(4.0),
        threePointFive(3.5),
        three(3.0),
        twoPointFive(2.5),
        two(2.0),
        one(1.0),
        zero(0.0),
        invalid(-1);

        double gpa;
        gpa(double gpa)
        {
            this.gpa = gpa;
        }

        @NonNull
        @Override
        public String toString() {
            return String.valueOf(gpa);
        }

        public double getGpa() {
            return gpa;
        }

        //Takes a valid gpa double and returns the closest value in the enum
        public static gpa getGpa( double target) {
            gpa[] GPAs = values();
            int idx = 0;
            double dist = Math.abs(GPAs[0].gpa - target);

            for (int i = 1; i< GPAs.length; i++) {
                double cDist = Math.abs(GPAs[i].gpa - target);

                if (cDist < dist) {
                    idx = i;
                    dist = cDist;
                }
            }

            return GPAs[idx];
        }
    }

    //constructor
    public StudentSection(String studentID, grades newGrade, Section newSection, Course associatedCourse) {
        associatedStudent = studentID;
        grade = newGrade;
        section = newSection;
        this.associatedCourse = associatedCourse;
    }

    //getter
    public Section getSection() {
        return section;
    }

    public String getAssociatedStudent() {
        return associatedStudent;
    }

    public grades getGrade() {
        return grade;
    }

    //returns credit hours for the course linked to this student section
    public int getCreditHours(){
        return associatedCourse.getCreditHours();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object another) {
        StudentSection temp = (StudentSection) another;
        return associatedStudent.equals(temp.associatedStudent) && grade.equals(temp.grade) && section.getSection().equals(temp.section.getSection());
    }

    public Course getAssociatedCourse() {
        return associatedCourse;
    }
}
