package com.group_15.bta.persistence;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class StudentSectionPersistenceStub implements StudentSectionPersistence {
    private ArrayList<StudentSection> studentSections;

    public StudentSectionPersistenceStub() {
        this.studentSections = new ArrayList<>();

        Course comp3350Course = new Course("COMP 3350", "Software Engineering I", "Introduction to software engineering. Software life cycle models, system and software requirements analysis, specifications, software design, testing and maintenance, software quality.", 3, "Computer Science", 1782.25, "B.Sc. (Hons)");
        Course biol1300Course = new Course("BIOL 1300", "Economic Plants", "A survey of economically important plants and their products. The history of plant use, plants in folklore and medicine, fermentation and viticulture, domestication of plants, and forestry are the major topics covered. Chemical, structural, and nutritional aspects of plant products are also discussed.", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)");
        Course math1500Course = new Course("MATH 1500", "Introduction to Calculus", "(Lab Required) Differentiation and integration of elementary functions, with applications to maxima and minima, rates of change, area, and volume", 3, "Mathematics", 1782.25, "B.Sc. (Hons)");
        Course engl1400Course = new Course("ENGL 1400", "Thematic Approaches to the Study of Literature", "An introduction to the study of literature, with emphasis on the development of reading and writing skills. Poetry, prose, and drama from various thematic perspectives.", 3, "English", 1782.25, "B.Sc. (Hons)");
        Course chem1110Course = new Course("CHEM 1110", "Introductory Chemistry 2: Interaction, Reactivity, and Chemical Properties", "This course builds upon students' foundation in chemistry to give them a better understanding of chemical reactivity and physical properties.", 3, "Chemistry", 1782.25, "B.Sc. (Hons)");
        Course math1300Course = new Course("MATH 1300", "Vector Geometry and Linear Algebra", "(Lab Required) An introduction to vectors, matrices, systems of linear equations and three-dimensional geometry.", 3, "Mathematics", 1782.25, "B.Sc. (Hons)");
        Course comp1012Course =  new Course("COMP 1012", "Computer Programming for Scientists and Engineers", "(Lab Required) An introduction to computer programming suitable for solving problems in science and engineering. Students will implement algorithms for numerical processing, statistical analysis and matrix operations", 3, "Computer Science", 1782.25, "B.Sc. (Hons)");
        Course biol1010Course = new Course("BIOL 1010", "Biology: Biological Diversity and Interactions", "An introduction to biological diversity including prokaryotes, protists, fungi, plants and animals; the form and function of plants and animals and basic concepts of ecology", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)");
        Section comp3350 = new Section("COMP 3350 - A01", "Dr. Heather Matheson", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 10, 500, "COMP 3350", "Computer Science");
        Section biol1300 = new Section("BIOL 1300 - A01", "Micolash", new String[]{"Tuesday", " Thursday"}, new String[]{"11:30 AM - 12:45 PM", " 11:30 AM - 12:45 PM"}, "Remote", 10, 200, "BIOL 1300", "Biological Sciences");
        Section math1500 = new Section("MATH 1500 - A01", "Eilo", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM", " 11:30 AM - 12:20 PM"}, "Remote", 5, 200, "MATH 1500", "Mathematics");
        Section math1500Lab = new Section("MATH 1500 - B04", "Wallar", new String[]{"Tuesday"}, new String[]{"2:30 PM - 3:45 PM"}, "Remote", 2, 40, "MATH 1500", "Mathematics");
        Section engl1400 = new Section("ENGL 1400 - A04", "Galhad", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 3, 40, "ENGL 1400", "English");
        Section chem1110 = new Section("CHEM 1110 - A02", "Gehrman", new String[]{"Tuesday", " Thursday"}, new String[]{"8:30 AM - 9:45 AM", " 8:30 AM - 9:45 AM"}, "Remote", 3, 221, "CHEM 1110", "Chemistry");
        Section math1300 = new Section("MATH 1300 - A02", "Wallar", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM", " 9:30 AM - 10:20 AM"}, "Remote", 5, 175, "MATH 1300", "Mathematics");
        Section comp1012 = new Section("COMP 1012 - B01", "Siegward", new String[]{"Wednesday"}, new String[]{"8:30 AM - 9:20 AM"}, "Remote", 2, 16, "COMP 1012", "Computer Science");
        Section biol1010 = new Section("BIOL 1010 - A01", "Gascoigne", new String[]{"Monday", " Wednesday", " Friday"}, new String[]{"10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM", " 10:30 AM - 11:20 AM"}, "Remote", 10, 198, "BIOL 1010", "Biological Sciences");
        studentSections.add(new StudentSection("student", "In Progress", comp3350, comp3350Course));
        studentSections.add(new StudentSection("student", "In Progress", biol1300, biol1300Course));
        studentSections.add(new StudentSection("student", "In Progress", math1500, math1500Course));
        studentSections.add(new StudentSection("student", "In Progress", math1500Lab, math1500Course));
        studentSections.add(new StudentSection("student", "In Progress", engl1400, engl1400Course));
        studentSections.add(new StudentSection("student", "A+", chem1110, chem1110Course));
        studentSections.add(new StudentSection("student", "C+", math1300, math1300Course));
        studentSections.add(new StudentSection("student", "C", comp1012, comp1012Course));
        studentSections.add(new StudentSection("student", "B", biol1010, biol1010Course));
    }
    @Override
    public ArrayList<StudentSection> getStudentSectionList() {
        return studentSections;
    }

    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID, boolean inProgress) {
        ArrayList<StudentSection> toReturn = new ArrayList<>();

        if(inProgress)
        {
            for(int i=0; i<studentSections.size(); i++)
            {
                if(studentSections.get(i).getAssociatedStudent().equals(studentID) && studentSections.get(i).getGrade().equals("In Progress"))
                {
                    toReturn.add(studentSections.get(i));
                }
            }
        }
        else
        {
            for(int i=0; i<studentSections.size(); i++)
            {
                if(studentSections.get(i).getAssociatedStudent().equals(studentID) && !studentSections.get(i).getGrade().equals("In Progress"))
                {
                    toReturn.add(studentSections.get(i));
                }
            }
        }

        return toReturn;
    }

    @Override
    public ArrayList<StudentSection> getStudentsInSection(String section) {
        ArrayList<StudentSection> studentToReturn = new ArrayList<>();
        for (int i = 0; i < studentSections.size(); i++) {
            if (studentSections.get(i).getSection().getSection().equals(section)) {studentToReturn.add(studentSections.get(i));}
        }
        return studentToReturn;
    }

    @Override
    public void updateStudentSection(StudentSection currentStudentSection) {
        int index;

        index = studentSections.indexOf(currentStudentSection);
        if (index >= 0) {
            studentSections.set(index, currentStudentSection);
        }
    }

    @Override
    public void insertSection(StudentSection currentStudentSection) {
        // don't bother checking for duplicates
        studentSections.add(currentStudentSection);
    }

    @Override
    public void deleteSection(StudentSection currentStudentSection) {
        int index;

        index = studentSections.indexOf(currentStudentSection);
        if (index >= 0)
        {
            studentSections.remove(index);
        }
    }

    @Override
    public ArrayList<Section> getSectionList(String studentID, boolean inProgress) {
        ArrayList<Section> toReturn = new ArrayList<>();

        if(inProgress)
        {
            for(int i=0; i<studentSections.size(); i++)
            {
                if(studentSections.get(i).getAssociatedStudent().equals(studentID) && studentSections.get(i).getGrade().equals("In Progress"))
                {
                    toReturn.add(studentSections.get(i).getSection());
                }
            }
        }
        else
        {
            for(int i=0; i<studentSections.size(); i++)
            {
                if(studentSections.get(i).getAssociatedStudent().equals(studentID) && !studentSections.get(i).getGrade().equals("In Progress"))
                {
                    toReturn.add(studentSections.get(i).getSection());
                }
            }
        }

        return toReturn;
    }

    @Override
    public ArrayList<Section> getSectionList(String studentID) {
        ArrayList<Section> toReturn = new ArrayList<>();

        for(int i=0; i<studentSections.size(); i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(studentID))
            {
                toReturn.add(studentSections.get(i).getSection());
            }

        }

        return toReturn;
    }

    @Override
    public ArrayList<Course> getCourses(String studentID) {
        ArrayList<Course> toReturn = new ArrayList<>();

        for(int i=0; i<studentSections.size(); i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(studentID))
            {
                toReturn.add(studentSections.get(i).getAssociatedCourse());
            }
        }
        return toReturn;
    }

    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID){
        ArrayList<StudentSection> toReturn = new ArrayList<>();
        for(int i=0; i<studentSections.size(); i++)
        {
            if(studentSections.get(i).getAssociatedStudent().equals(studentID))
            {
                toReturn.add(studentSections.get(i));
            }
        }
        return toReturn;

    }

}