package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentSectionPersistenceHSQLDB implements StudentSectionPersistence, Serializable {

    private String dbPath;
    private Connection existingConnection = null;

    public StudentSectionPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    public StudentSectionPersistenceHSQLDB(Connection newConnection) {
        existingConnection = newConnection;
    }

    private Connection connection() throws SQLException {
        Connection toReturn;

        if (existingConnection == null) {
            toReturn = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
        } else {
            toReturn = existingConnection;
        }

        return toReturn;
    }

    public StudentSection fromResultSet(final ResultSet rs) throws SQLException {
        final String studentID = rs.getString("STUDENTID");
        final StudentSection.grades grade = StudentSection.grades.getEnum(rs.getString("GRADE"));
        final String sectionID = rs.getString("SECTIONID");
        final String courseID = rs.getString("COURSEID");
        SectionPersistenceHSQLDB sectionGetter = new SectionPersistenceHSQLDB(connection());
        Section section = sectionGetter.getSection(sectionID);
        CoursePersistenceHSQLDB courseGetter = new CoursePersistenceHSQLDB(connection());
        Course course  = courseGetter.getCourse(courseID);
        return new StudentSection(studentID, grade, section, course);

    }

    /**
     * getStudentSectionList
     * gets all the student sections in the database
     * @return - an arraylist with all the student sections in the database
     */
    @Override
    public ArrayList<StudentSection> getStudentSectionList() {

        ArrayList<StudentSection> toReturn = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT * FROM STUDENTSECTIONS");
            while (newResultSet.next()) {
                final StudentSection section = fromResultSet(newResultSet);
                toReturn.add(section);
            }

            newResultSet.close();
            newStatement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }

    /**
     * getStudentSectionList
     * gets all the student sections that are graded or ungraded in the database
     * @param studentID - the student id we want the student sections for
     * @param inProgress - true if we want the student sections to be ungraded, false if graded
     * @return - an arraylist with all the student sections in the database with the same grade status
     */
    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID, boolean inProgress) {

        ArrayList<StudentSection> toReturn = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            PreparedStatement preparedStatement;
            if(inProgress)
            {
                preparedStatement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = 'In Progress'");
            }
            else
            {
                preparedStatement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE != 'In Progress'");
            }
            preparedStatement.setString(1, studentID);
            final ResultSet newResultSet = preparedStatement.executeQuery();

            while (newResultSet.next()) {
                final StudentSection studentSection = fromResultSet(newResultSet);
                toReturn.add(studentSection);
            }

            newResultSet.close();
            preparedStatement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }

    /**
     * getSectionList
     * gets all the sections that are graded or ungraded in the database
     * @param studentID - the student id we want the student sections for
     * @param inProgress - true if we want the student sections to be ungraded, false if graded
     * @return - an arraylist with all the sections in the database with the same grade status
     */
    @Override
    public ArrayList<Section> getSectionList(String studentID, boolean inProgress) {

        ArrayList<Section> toReturn = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            PreparedStatement preparedStatement;
            if(inProgress)
            {
                preparedStatement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = 'In Progress'");
            }
            else
            {
                preparedStatement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE != 'In Progress'");
            }
            preparedStatement.setString(1, studentID);
            final ResultSet newResultSet = preparedStatement.executeQuery();

            while (newResultSet.next()) {
                final StudentSection studentSection = fromResultSet(newResultSet);
                toReturn.add(studentSection.getSection());
            }

            newResultSet.close();
            preparedStatement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }

    /**
     * getStudentSectionList
     * gets all the student sections in the database for a particular student
     * @param studentID - the student id we want the student sections for
     * @return - an arraylist with all the student sections for one student in the database
     */
    @Override
    public ArrayList<StudentSection> getStudentSectionList(String studentID) {
        final ArrayList<StudentSection> toReturn = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            st.setString(1, studentID);
            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final StudentSection studentSection = fromResultSet(rs);
                toReturn.add(studentSection);
            }

            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }


        return toReturn;
    }

    /**
     * getSectionList
     * gets all the sections in the database for a particular student
     * @param studentID - the student id we want the sections for
     * @return - an arraylist with all the sections for one student in the database
     */
    @Override
    public ArrayList<Section> getSectionList(String studentID) {
        final ArrayList<Section> toReturn = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            st.setString(1, studentID);
            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final StudentSection studentSection = fromResultSet(rs);
                toReturn.add(studentSection.getSection());
            }

            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }


        return toReturn;
    }


    /**
     * getCourses
     * gets all the courses in the database for a particular student
     * @param studentID - the student id we want the courses for
     * @return - an arraylist with all the courses for one student in the database
     */
    @Override
    public ArrayList<Course> getCourses(String studentID) {
        ArrayList<Course> toReturn = new ArrayList<>();
        try(Connection newConnection = connection())
        {

            PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM COURSES WHERE COURSEID IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ?)");
            statement.setString(1, studentID);
            ResultSet rs = statement.executeQuery();
            CoursePersistenceHSQLDB courseParser = new CoursePersistenceHSQLDB(newConnection);
            while (rs.next())
            {
                toReturn.add(courseParser.fromResultSet(rs));
            }
            rs.close();
            statement.close();
        }
        catch(final SQLException newException)
        {
            throw new PersistenceException(newException);
        }
        return toReturn;
    }


    /**
     * getStudentsInSection
     * gets all the student sections in the database for a particular section
     * @param courseID - the course id we want the student sections for
     * @return - an arraylist with all the student sections for one section in the database
     */
    @Override
    public ArrayList<StudentSection> getStudentsInSection(String courseID) {
        final ArrayList<StudentSection> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS,STUDENTSECTIONS WHERE STUDENTS.USERNAME=STUDENTSECTIONS.STUDENTID AND SECTIONID = ?");
            st.setString(1, courseID);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final StudentSection record = fromResultSet(rs);
                students.add(record);
            }

            rs.close();
            st.close();


        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }

        return students;
    }

    /**
     * updateStudentSection
     * update a student section in the database
     * @param currentSection- student section to be updated
     */
    @Override
    public void updateStudentSection(StudentSection currentSection) {
        try (final Connection newConnection = connection()) {

            final PreparedStatement statement = newConnection.prepareStatement("UPDATE STUDENTSECTIONS SET GRADE = ?  WHERE STUDENTID = ? AND SECTIONID = ?");
            statement.setString(1, currentSection.getGrade().toString());
            statement.setString(2, currentSection.getAssociatedStudent());
            statement.setString(3, currentSection.getSection().getSection());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * insertSection
     * inserts a student section into the database
     * @param currentSection - student section to be inserted
     */
    @Override
    public void insertSection(StudentSection currentSection) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTSECTIONS VALUES(?, ?, ?, ?)");
            statement.setString(1, currentSection.getAssociatedStudent());
            statement.setString(2, currentSection.getGrade().toString());
            statement.setString(3, currentSection.getSection().getSection());
            statement.setString(4, currentSection.getAssociatedCourse().getID());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * deleteSection
     * deletes a student section from the database
     * @param toRemove - student section to be deleted
     */
    @Override
    public void deleteSection(StudentSection toRemove) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = ? AND SECTIONID = ?");
            statement.setString(1, toRemove.getAssociatedStudent());
            statement.setString(2, toRemove.getGrade().toString());
            statement.setString(3, toRemove.getSection().getSection());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
