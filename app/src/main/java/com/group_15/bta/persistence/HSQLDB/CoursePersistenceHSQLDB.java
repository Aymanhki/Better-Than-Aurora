package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CoursePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CoursePersistenceHSQLDB implements CoursePersistence {

    private String dbPath;

    public CoursePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Courses fromResultSet(final ResultSet rs) throws SQLException {
        final String courseID = rs.getString("COURSEID");
        final String courseName = rs.getString("TITLE");
        final String category = rs.getString("NAME");
        return new Courses(courseID, courseName, category);
    }

    @Override
    public ArrayList<Courses> getCourseList() {
        final ArrayList<Courses> courses = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT * FROM COURSES");
            while (newResultSet.next()) {
                final Courses course = fromResultSet(newResultSet);

                SectionPersistenceHSQLDB sectionsGetter = new SectionPersistenceHSQLDB(dbPath);
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
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO COURSES VALUES(?, ?)");
            statement.setString(1, currentCourse.getID());
            statement.setString(2, currentCourse.getTitle());
            statement.executeUpdate();
            SectionPersistenceHSQLDB sectionInserter = new SectionPersistenceHSQLDB(dbPath);
            for (int i = 0; i < currentCourse.getSections().size(); i++) {
                sectionInserter.insertSection(currentCourse.getSections().get(i));
            }

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
