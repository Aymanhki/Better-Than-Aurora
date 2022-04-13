package com.group_15.bta.persistence;

import com.group_15.bta.objects.Course;

import java.util.ArrayList;

public class CoursePersistenceStub implements CoursePersistence {
    private ArrayList<Course> courses;

    public CoursePersistenceStub() {
        this.courses = new ArrayList<>();
        courses.add(new Course("BIOL 1000", "Biology: Foundations of Life", "A course in unifying principles of biology including cell biology, bioenergetics, cell division, genetics and evolution", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("BIOL 1010", "Biology: Biological Diversity and Interactions", "An introduction to biological diversity including prokaryotes, protists, fungi, plants and animals; the form and function of plants and animals and basic concepts of ecology", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("BIOL 1300", "Economic Plants", "A survey of economically important plants and their products. The history of plant use, plants in folklore and medicine, fermentation and viticulture, domestication of plants, and forestry are the major topics covered. Chemical, structural, and nutritional aspects of plant products are also discussed.", 3, "Biological Sciences", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("CHEM 1100", "Introductory Chemistry 1: Atomic and Molecular Structure and Energetics", "This course provides a basic understanding of the fundamentals of chemistry. By the end of this course, students will understand the periodic table, energy in chemistry, atomic and molecular structures, and the concept of chemical reactivity.", 3, "Chemistry", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("CHEM 1110", "Introductory Chemistry 2: Interaction, Reactivity, and Chemical Properties", "This course builds upon students' foundation in chemistry to give them a better understanding of chemical reactivity and physical properties.", 3, "Chemistry", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("CHEM 1120", "Introduction to Chemical Techniques", "This course builds understanding in chemistry through active learning in the lab. By performing lab experiments, students will gain skills in making observations, safe handling of chemicals, handling laboratory equipment, quantitative analysis, data processing, and scientific communication", 3, "Chemistry", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("COMP 1010", "Introductory Computer Science 1", "(Lab Required) An introduction to computer programming using a procedural high level language.", 3, "Computer Science", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("COMP 1012", "Computer Programming for Scientists and Engineers", "(Lab Required) An introduction to computer programming suitable for solving problems in science and engineering. Students will implement algorithms for numerical processing, statistical analysis and matrix operations", 3, "Computer Science", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("COMP 3350", "Software Engineering I", "Introduction to software engineering. Software life cycle models, system and software requirements analysis, specifications, software design, testing and maintenance, software quality.", 3, "Computer Science", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("ENGL 1200", "Representative Literary Works", "An introduction to the study of literature, with emphasis on the development of reading and writing skills. Poetry, prose and drama from various historical periods.", 6, "English", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("ENGL 1340", "Introduction to Literary Analysis", "This course is intended to provide students with reading, writing, and analytic skills required for literary studies.", 3, "English", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("ENGL 1400", "Thematic Approaches to the Study of Literature", "An introduction to the study of literature, with emphasis on the development of reading and writing skills. Poetry, prose, and drama from various thematic perspectives.", 3, "English", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("MATH 1300", "Vector Geometry and Linear Algebra", "(Lab Required) An introduction to vectors, matrices, systems of linear equations and three-dimensional geometry.", 3, "Mathematics", 1782.25, "B.Sc. (Hons)"));
        courses.add(new Course("MATH 1500", "Introduction to Calculus", "(Lab Required) Differentiation and integration of elementary functions, with applications to maxima and minima, rates of change, area, and volume", 3, "Mathematics", 1782.25, "B.Sc. (Hons)"));
    }

    @Override
    public ArrayList<Course> getCourseList() {
        return courses;
    }

    @Override
    public ArrayList<Course> getCategoryCourses(String catName){return courses;}

    @Override
    public Course getCourse(String courseID) {
        Course toReturn = null;

        for(int i=0; i<courses.size(); i++)
        {
            if(courses.get(i).getID().equals(courseID))
            {
                toReturn = courses.get(i);
            }
        }

        return toReturn;
    }

    @Override
    public void insertCourses(Course currentCourse) {
        // don't bother checking for duplicates
        courses.add(currentCourse);
    }

    @Override
    public void updateCourse(Course currentCourse) {
        int index;

        index = courses.indexOf(currentCourse.getID());
        if (index >= 0)
        {
            courses.set(index, currentCourse);
        }
    }

    @Override
    public void deleteCourses(Course currentCourse) {
        int index;

        index = courses.indexOf(currentCourse);
        if (index >= 0)
        {
            courses.remove(index);
        }
    }
}
