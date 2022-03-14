package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SectionPersistenceHSQLDB implements SectionPersistence {

    private String dbPath;

    public SectionPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Section fromResultSet(final ResultSet rs) throws SQLException {
        final String sectionID = rs.getString("SECTIONID");
        final String instructor = rs.getString("INSTRUCTOR");
        final String[] days = rs.getString("DAYS").split(",");
        final String[] times = rs.getString("TIMES").split(",");
        final String location = rs.getString("LOCATION");
        final int available = rs.getInt("AVAILABLE");
        final int capacity = rs.getInt("CAPACITY");
        final String grade = rs.getString("GRADE");
        final String associatedStudent = rs.getString("STUDENTID");
        final String associatedCourse = rs.getString("COURSEID");
        final String associatedCategory = rs.getString("NAME");
        return new Section(sectionID, instructor, days, times, location, available, capacity, grade, associatedStudent, associatedCourse, associatedCategory);
    }

    @Override
    public ArrayList<Section> getSectionList() {
        final ArrayList<Section> sections = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT FROM * SECTIONS");
            while (newResultSet.next()) {
                final Section section = fromResultSet(newResultSet);
                sections.add(section);
            }

            newResultSet.close();
            newStatement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sections;
    }


    @Override
    public void insertSection(Section currentSection) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO SECTIONS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, currentSection.getSection());
            statement.setString(2, currentSection.getInstructor());

            String tempDays[] = currentSection.getDaysRaw();
            String daysToAdd = "";
            for (int i = 0; i < tempDays.length; i++) {
                daysToAdd += tempDays[i];

                if (i < tempDays.length - 1) {
                    daysToAdd += ", ";
                }
            }
            statement.setString(3, daysToAdd);

            String tempTimes[] = currentSection.getTimes();
            String timesToAdd = "";
            for (int i = 0; i < tempTimes.length; i++) {
                timesToAdd += tempTimes[i];

                if (i < tempTimes.length - 1) {
                    timesToAdd += ", ";
                }
            }
            statement.setString(4, timesToAdd);
            statement.setString(5, currentSection.getLocation());
            statement.setString(6, Integer.toString(currentSection.getAvailable()));
            statement.setString(7, Integer.toString(currentSection.getCAP()));
            statement.setString(8, currentSection.getGrade());
            statement.setString(9, currentSection.getAssociatedStudent());
            statement.setString(10, currentSection.getAssociatedCourse());
            statement.setString(11, currentSection.getAssociatedCategory());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteSection(Section toRemove) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("DELETE FROM SECTIONS WHERE SECTIONID = ?");
            statement.setString(1, toRemove.getSection());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
