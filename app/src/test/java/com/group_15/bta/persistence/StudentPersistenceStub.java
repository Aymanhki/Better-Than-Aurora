package com.group_15.bta.persistence;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class StudentPersistenceStub implements StudentPersistence {
    private final ArrayList<Student> students;
    private final ArrayList<StudentSection> studentSections;
    private final ArrayList<Course> courses;

    public StudentPersistenceStub() {
        this.students = new ArrayList<>();
        students.add(new Student("student", "student", "Ayman", "B.Sc. (Hons)"));
        students.add(new Student("student1", "student1", "Nilin", "B.Sc. (Hons)"));
        students.add(new Student("student2", "student2", "Dara", "B.Sc. (Hons)"));
        students.add(new Student("student3", "student3", "Amelia", "B.Sc. (Hons)"));

        this.studentSections = new ArrayList<>();

        Course comp3350Course = new Course("COMP 3350", "Software Engineering I", "Introduction to software engineering. Software life cycle models, system and software requirements analysis, specifications, software design, testing and maintenance, software quality.", 3, "Computer Science", 1782.25, "B.Sc. (Hons)");
        Course biol1300Course = new Course("BIOL 1300", "Economic Plants", "A survey of economically important plants and their products. The history of plant use, plants in folklore and medicine, fermentation and viticulture, domestication of plants, and forestry are the major topics covered. Chemical, structural, and nutritional aspects of plant products are also discussed.", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)");
        Course math1500Course = new Course("MATH 1500", "Introduction to Calculus", "(Lab Required) Differentiation and integration of elementary functions, with applications to maxima and minima, rates of change, area, and volume", 3, "Mathematics", 1782.25, "B.Sc. (Hons)");
        Course engl1400Course = new Course("ENGL 1400", "Thematic Approaches to the Study of Literature", "An introduction to the study of literature, with emphasis on the development of reading and writing skills. Poetry, prose, and drama from various thematic perspectives.", 3, "English", 1782.25, "B.Sc. (Hons)");
        Course chem1110Course = new Course("CHEM 1110", "Introductory Chemistry 2: Interaction, Reactivity, and Chemical Properties", "This course builds upon students' foundation in chemistry to give them a better understanding of chemical reactivity and physical properties.", 3, "Chemistry", 1782.25, "B.Sc. (Hons)");
        Course math1300Course = new Course("MATH 1300", "Vector Geometry and Linear Algebra", "(Lab Required) An introduction to vectors, matrices, systems of linear equations and three-dimensional geometry.", 3, "Mathematics", 1782.25, "B.Sc. (Hons)");
        Course comp1012Course =  new Course("COMP 1012", "Computer Programming for Scientists and Engineers", "(Lab Required) An introduction to computer programming suitable for solving problems in science and engineering. Students will implement algorithms for numerical processing, statistical analysis and matrix operations", 3, "Computer Science", 1782.25, "B.Sc. (Hons)");
        Course biol1010Course = new Course("BIOL 1010", "Biology: Biological Diversity and Interactions", "An introduction to biological diversity including prokaryotes, protists, fungi, plants and animals; the form and function of plants and animals and basic concepts of ecology", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)");
        Section comp3350 = new Section("COMP 3350 - A01", "Dr. Heather Matheson", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.perfectEarlyBird, "Remote", 10, 500, "COMP 3350", "Computer Science");
        Section biol1300 = new Section("BIOL 1300 - A01", "Micolash", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday}, Section.availableSectionTimes.noLongerEarlyBird, "Remote", 10, 200, "BIOL 1300", "Biological Sciences");
        Section math1500 = new Section("MATH 1500 - A01", "Eilo", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.barelyEarlyBird, "Remote", 5, 200, "MATH 1500", "Mathematics");
        Section math1500Lab = new Section("MATH 1500 - B04", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday}, Section.availableSectionTimes.longLikesSleepingAfternoonBird, "Remote", 2, 40, "MATH 1500", "Mathematics");
        Section engl1400 = new Section("ENGL 1400 - A04", "Galhad", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 3, 40, "ENGL 1400", "English");
        Section chem1110 = new Section("CHEM 1110 - A02", "Gehrman", new Section.availableSectionDays[]{Section.availableSectionDays.Tuesday, Section.availableSectionDays.Thursday},  Section.availableSectionTimes.earlyBirdWithCoffee, "Remote", 3, 221, "CHEM 1110", "Chemistry");
        Section math1300 = new Section("MATH 1300 - A02", "Wallar", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.perfectEarlyBird, "Remote", 5, 175, "MATH 1300", "Mathematics");
        Section comp1012 = new Section("COMP 1012 - B01", "Siegward", new Section.availableSectionDays[]{Section.availableSectionDays.Wednesday}, Section.availableSectionTimes.earlyBird, "Remote", 2, 16, "COMP 1012", "Computer Science");
        Section biol1010 = new Section("BIOL 1010 - A01", "Gascoigne", new Section.availableSectionDays[]{Section.availableSectionDays.Monday, Section.availableSectionDays.Wednesday, Section.availableSectionDays.Friday}, Section.availableSectionTimes.likesSleepingEarlyBird, "Remote", 10, 198, "BIOL 1010", "Biological Sciences");
        studentSections.add(new StudentSection("student", StudentSection.grades.IP, comp3350, comp3350Course));
        studentSections.add(new StudentSection("student2", StudentSection.grades.IP, comp3350, comp3350Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.IP, biol1300, biol1300Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.IP, math1500, math1500Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.IP, math1500Lab, math1500Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.IP, engl1400, engl1400Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.APlus, chem1110, chem1110Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.CPlus, math1300, math1300Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.C, comp1012, comp1012Course));
        studentSections.add(new StudentSection("student", StudentSection.grades.B, biol1010, biol1010Course));

        this.courses = new ArrayList<>();
        courses.add(comp3350Course);
        courses.add(biol1300Course);
        courses.add(math1500Course);
        courses.add(engl1400Course);
        courses.add(chem1110Course);
        courses.add(math1300Course);
        courses.add(comp1012Course);
        courses.add(biol1010Course);

    }

    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }

    @Override
    public ArrayList<Student> getStudent(Student student) {
        ArrayList<Student> studentToReturn = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(student.getID())) {studentToReturn.add(students.get(i));}
        }
        return studentToReturn;
    }

    @Override
    public void updateStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0) {
            students.set(index, currentStudent);
        }
    }

    @Override
    public void insertStudent(Student currentStudent) {
        // don't bother checking for duplicates
        students.add(currentStudent);
    }

    @Override
    public void deleteStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            students.remove(index);
        }
    }

    @Override
    public void deleteStudentID(String currentStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(currentStudent)) {students.remove(i);}
        }
    }


    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student) {
        ArrayList<Course> toReturn = new ArrayList<>();

        for(int i=0; i<courses.size(); i++)
        {
            if(courses.get(i).getAssociatedDegree().equals(student.getAssociatedDegree()))
            {
                boolean found = false;
                for(int j=0; j<studentSections.size() && !found; j++)
                {
                    if(studentSections.get(j).getAssociatedStudent().equals(student.getID()) &&
                            studentSections.get(i).getAssociatedCourse().equals(courses.get(i)))
                    {
                        found = true;
                    }
                }

                if(!found)
                {
                    toReturn.add(courses.get(i));
                }
            }
        }

        return toReturn;
    }


    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        StudentSection toReturn = null;

        for(int i=0; i<studentSections.size() && toReturn == null; i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(student.getID()) &&
            studentSections.get(i).getSection().equals(section))
            {
                toReturn = studentSections.get(i);
            }
        }

        return toReturn;
    }


    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student) {
        ArrayList<PieEntry> degreeBreakDown = new ArrayList<>();

        int studentDegreeTakenCredit = 0;
        for(int i=0; i<studentSections.size(); i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(student.getID()) &&
            studentSections.get(i).getAssociatedCourse().associatedWithDegree(student.getAssociatedDegree()) &&
            !studentSections.get(i).getGrade().equals(StudentSection.grades.IP))
            {
                studentDegreeTakenCredit += studentSections.get(i).getAssociatedCourse().getCreditHours();
            }
        }
        degreeBreakDown.add(new PieEntry(studentDegreeTakenCredit, "Complete"));

        int studentDegreeInProgressCredit = 0;
        for(int i=0; i<studentSections.size(); i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(student.getID()) &&
                    studentSections.get(i).getAssociatedCourse().associatedWithDegree(student.getAssociatedDegree()) &&
                    studentSections.get(i).getGrade().equals(StudentSection.grades.IP))
            {
                studentDegreeInProgressCredit += studentSections.get(i).getAssociatedCourse().getCreditHours();
            }
        }
        degreeBreakDown.add(new PieEntry(studentDegreeInProgressCredit, "In Progress"));

        int studentDegreeNotTakenCredit = 0;
        for(int i=0; i<courses.size(); i++)
        {
            if(courses.get(i).getAssociatedDegree().equals(student.getAssociatedDegree()))
            {
                boolean found = false;
                for(int j=0; j<studentSections.size() && !found; j++)
                {
                    if(studentSections.get(j).getAssociatedStudent().equals(student.getID()) &&
                            studentSections.get(i).getAssociatedCourse().equals(courses.get(i)))
                    {
                        found = true;
                    }
                }

                if(!found)
                {
                    studentDegreeNotTakenCredit += courses.get(i).getCreditHours();
                }
            }
        }
        degreeBreakDown.add(new PieEntry(studentDegreeNotTakenCredit, "Unfulfilled"));

        return degreeBreakDown;
    }


}
