package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CoursePersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CoursePersistenceHSQLDB implements CoursePersistence, Serializable {

    private String dbPath;
    private Connection existingConnection = null;

    public CoursePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    public CoursePersistenceHSQLDB(Connection newConnection) {
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

    private Courses fromResultSet(final ResultSet rs) throws SQLException {
        final String courseID = rs.getString("COURSEID");
        final String courseName = rs.getString("TITLE");
        final String courseDescription = rs.getString("DESCRIPTION");
        final String category = rs.getString("NAME");
        final int credit = rs.getInt("CREDIT");
        return new Courses(courseID, courseName, courseDescription, credit, category);
    }

    @Override
    public ArrayList<Courses> getCourseList() {
        final ArrayList<Courses> courses = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT * FROM COURSES");
            while (newResultSet.next()) {
                final Courses course = fromResultSet(newResultSet);

                SectionPersistenceHSQLDB sectionsGetter = new SectionPersistenceHSQLDB(newConnection);
                ArrayList<Section> sections = sectionsGetter.getSectionList();

                for (int i = 0; i < sections.size(); i++) {
                    if (sections.get(i).getAssociatedCourse().equals(course.getID())) {
                        course.addSection(sections.get(i));
                    }
                }

                courses.add(course);
            }

            newResultSet.close();
            newStatement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return courses;
    }


    @Override
    public void insertCourses(Courses currentCourse) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO COURSES VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, currentCourse.getID());
            statement.setString(2, currentCourse.getTitle());
            statement.setString(3, currentCourse.getDescription());
            statement.setInt(4, currentCourse.getCreditHours());
            statement.setString(5, currentCourse.getAssociatedCategory());

            statement.executeUpdate();
            SectionPersistenceHSQLDB sectionInserter = new SectionPersistenceHSQLDB(newConnection);
            for (int i = 0; i < currentCourse.getSections().size(); i++) {
                sectionInserter.insertSection(currentCourse.getSections().get(i));
            }

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void updateCourse(Courses currentCourse) {
        try (final Connection newConnection = connection()) {
            //COURSEID , TITLE , DESCRIPTION , CREDIT,  NAME VARCHAR(100))
            final PreparedStatement statement = newConnection.prepareStatement("UPDATE COURSES SET TITLE = ?, DESCRIPTION = ?, CREDIT = ?, NAME = ?  WHERE COURSEID = ?");
            statement.setString(1, currentCourse.getTitle());
            statement.setString(2, currentCourse.getDescription());
            statement.setString(3, String.valueOf(currentCourse.getCreditHours()));
            statement.setString(4, currentCourse.getAssociatedCategory());
            statement.setString(5, currentCourse.getID());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteCourses(Courses toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM SECTIONS WHERE COURSEID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM COURSES WHERE COURSEID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
