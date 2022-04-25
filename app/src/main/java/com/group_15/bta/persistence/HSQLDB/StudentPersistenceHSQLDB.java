package com.group_15.bta.persistence.HSQLDB;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentPersistenceHSQLDB implements StudentPersistence, Serializable {
    private final String dbPath;

    public StudentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }


    private Connection connection() throws SQLException {
        Connection toReturn;

        toReturn = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");

        return toReturn;
    }

    public Student fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("USERNAME");
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");
        final String associatedDegree = rs.getString("ASSOCIATEDDEGREE");
        return new Student(userName, password, name, associatedDegree);
    }

    /**
     * getStudentList
     * gets all the students in the database
     * @return - an arraylist with all the students in the database
     */
    @Override
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();

        try (Connection newConnection = connection()) {
            final Statement statement = newConnection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");
            while (resultSet.next()) {
                final Student student = fromResultSet(resultSet);
                students.add(student);
            }
            resultSet.close();
            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return students;
    }

    /**
     * getStudent
     * get a student from the database
     * @param currentStudent - the student we want
     * @return - student that we requested in a list (at pos 0)
     */
    @Override
    public ArrayList<Student> getStudent(Student currentStudent) {
        final ArrayList<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS WHERE USERNAME = ?");
            st.setString(1, currentStudent.getID());

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }


            rs.close();
            st.close();
            c.close();
            return students;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * updateStudent
     * update a student in the database
     * @param currentStudent- student to be updated
     */
    @Override
    public void updateStudent(Student currentStudent) {
        try (final Connection newConnection = connection()) {

            final PreparedStatement statement = newConnection.prepareStatement("UPDATE STUDENTS SET PASSWORD = ?, NAME = ?, ASSOCIATEDDEGREE = ?  WHERE USERNAME = ?");
            statement.setString(1, currentStudent.getPassword());
            statement.setString(2, currentStudent.getName());
            statement.setString(3, currentStudent.getAssociatedDegree());
            statement.setString(4, currentStudent.getID());
            statement.executeUpdate();

            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * insertStudent
     * inserts a student into the database
     * @param currentStudent - student to be inserted
     */
    @Override
    public void insertStudent(Student currentStudent) {

        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
            statement.setString(1, currentStudent.getPassword());
            statement.setString(2, currentStudent.getID());
            statement.setString(3, currentStudent.getName());
            statement.setString(4, currentStudent.getAssociatedDegree());
            statement.executeUpdate();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * deleteStudent
     * deletes a student from the database
     * @param toRemove - student to be deleted
     */
    @Override
    public void deleteStudent(Student toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE USERNAME = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();

            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * deleteStudentID
     * deletes a student with a particular id from the database
     * @param toRemove - string of student id that we want deleted
     */
    @Override
    public void deleteStudentID(String toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            statement.setString(1, toRemove);
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE USERNAME = ?");
            statement.setString(1, toRemove);
            statement.executeUpdate();

            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * getStudentDegreeInProgressCredit
     * returns the credit hours of the courses a student is currently enrolled in
     * @param student - student we want the credit hours for
     * @return - int representing the credit hours for the courses a student is currently working on
     */
    public int getStudentDegreeInProgressCredit(Student student)
    {
        int sumRequiredInProgressCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "In Progress");
            statement.setString(3, "%, "+studentDegree+"%");
            statement.setString(4, "%"+studentDegree+", %");
            statement.setString(5, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredInProgressCourses += rs.getInt("CREDIT");
            }


            rs.close();
            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredInProgressCourses;
    }

    /**
     * getStudentDegreeTakenCredit
     * gets the credit hours from the courses that a student has already completed
     * @param student - the student we want the credit hours for
     * @return - int representing the credit hours of the course a student has already taken
     */
    public int getStudentDegreeTakenCredit(Student student)
    {
        int sumRequiredTakenCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE != ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "In Progress");
            statement.setString(3, "%, "+studentDegree+"%");
            statement.setString(4, "%"+studentDegree+", %");
            statement.setString(5, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredTakenCourses += rs.getInt("CREDIT");
            }

            rs.close();
            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredTakenCourses;
    }

    /**
     * getStudentDegreeNotTakenCredit
     * gets the credit hours from the courses that a student has not already completed
     * @param student - the student we want the credit hours for
     * @return - int representing the credit hours for the courses a student has not already taken
     */
    public int getStudentDegreeNotTakenCredit(Student student)
    {
        int sumRequiredNotTakenCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID NOT IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "%, "+studentDegree+"%");
            statement.setString(3, "%"+studentDegree+", %");
            statement.setString(4, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredNotTakenCourses += rs.getInt("CREDIT");
            }


            rs.close();
            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredNotTakenCourses;
    }

    /**
     * getDegreeCreditBreakdown
     * returns an array list representing a chart that shows the degree progress for a student
     * @param student - the student we want to show degree progress for
     * @return - an arraylist with the values to show progress
     */
    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student)
    {
        ArrayList<PieEntry> degreeBreakDown = new ArrayList<>();
        degreeBreakDown.add(new PieEntry(getStudentDegreeTakenCredit(student), "Complete"));
        degreeBreakDown.add(new PieEntry(getStudentDegreeInProgressCredit(student), "In Progress"));
        degreeBreakDown.add(new PieEntry(getStudentDegreeNotTakenCredit(student), "Unfulfilled"));
        return degreeBreakDown;
    }

    /**
     * getStudentDegreeNotTakenCourses
     * gets the courses a student still needs in order to complete their degree
     * @param student - the student we want to find the courses they still need for
     * @return - an arraylist of courses representing the courses the student still needs for their degree
     */
    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student)
    {
        ArrayList<Course> nonTakenCourses = new ArrayList<>();
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM COURSES WHERE COURSEID NOT IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");

            statement.setString(1, student.getID());
            statement.setString(2, "%, "+studentDegree+"%");
            statement.setString(3, "%"+studentDegree+", %");
            statement.setString(4, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();

            SectionPersistenceHSQLDB sectionsGetter = new SectionPersistenceHSQLDB(newConnection);
            ArrayList<Section> sections = sectionsGetter.getSectionList();

            while (rs.next()) {
                final Course course = CoursePersistenceHSQLDB.fromResultSetStatic(rs);


                for (int i = 0; i < sections.size(); i++) {
                    if (sections.get(i).getAssociatedCourse().equals(course.getID())) {
                        course.addSection(sections.get(i));
                    }
                }

                nonTakenCourses.add(course);
            }


            rs.close();
            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return nonTakenCourses;
    }

    /**
     * getEnrolledSection
     * gets the student section for a student and a section
     * @param student - student we want the student section for
     * @param section - section we want the student section for
     * @return - the student section that is associated with the given student and section
     */
    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        StudentSection toReturn = null;

        try(Connection newConnection = connection())
        {
            PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND SECTIONID = ?");
            statement.setString(1, student.getID());
            statement.setString(2, section.getSection());
            ResultSet rs = statement.executeQuery();
            StudentSectionPersistenceHSQLDB parser = new StudentSectionPersistenceHSQLDB(newConnection);
            if(rs.next())
            {
                toReturn = parser.fromResultSet(rs);
            }


            rs.close();
            statement.close();
            newConnection.close();
        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }
}
