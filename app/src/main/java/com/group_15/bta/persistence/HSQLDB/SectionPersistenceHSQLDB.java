package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SectionPersistenceHSQLDB implements SectionPersistence, Serializable {

    private String dbPath;
    private Connection existingConnection = null;

    public SectionPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    public SectionPersistenceHSQLDB(Connection newConnection) {
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


    public Section fromResultSet(final ResultSet rs) throws SQLException {
        final String sectionID = rs.getString("SECTIONID");
        final String instructor = rs.getString("INSTRUCTOR");
        final Section.availableSectionDays[] sectionDays = Section.toDays(rs.getString("DAYS").split(","));
        final Section.availableSectionTimes time = Section.availableSectionTimes.getEnum(rs.getString("TIME"));
        final String location = rs.getString("LOCATION");
        final int available = rs.getInt("AVAILABLE");
        final int capacity = rs.getInt("CAPACITY");
        final String associatedCourse = rs.getString("COURSEID");
        final String associatedCategory = rs.getString("NAME");
        return new Section(sectionID, instructor, sectionDays, time, location, available, capacity, associatedCourse, associatedCategory);
    }

    /**
     * getSectionList
     * gets all the sections in the database
     * @return - an arraylist with all the sections in the database
     */
    @Override
    public ArrayList<Section> getSectionList() {
        final ArrayList<Section> sections = new ArrayList<>();

        try (final Connection newConnection = connection()) {
            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT * FROM SECTIONS");
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

    /**
     * getInstructorSections
     * gets all the sections under a particular instructor in the database
     * @return - an arraylist with all the sections taught by a instructor in the database
     */
    @Override
    public ArrayList<Section> getInstructorSections(String name) {
        final ArrayList<Section> sections = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM SECTIONS WHERE INSTRUCTOR = ?");
            st.setString(1, name);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final Section record = fromResultSet(rs);
                sections.add(record);
            }

            rs.close();
            st.close();

            return sections;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * getCourseSections
     * gets all the sections for a particular course in the database
     * @return - an arraylist with all the sections for a course in the database
     */
    @Override
    public ArrayList<Section> getCourseSections(String courseID) {
        final ArrayList<Section> sections = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM SECTIONS WHERE COURSEID = ?");
            st.setString(1, courseID);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final Section record = fromResultSet(rs);
                sections.add(record);
            }

            rs.close();
            st.close();

            return sections;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * insertSection
     * inserts a section into the database
     * @param currentSection - section to be inserted
     */
    @Override
    public void insertSection(Section currentSection) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO SECTIONS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, currentSection.getSection());
            statement.setString(2, currentSection.getInstructor());

            Section.availableSectionDays[] tempDays = currentSection.getDaysRaw();

            StringBuilder daysToAdd = new StringBuilder();
            for (int i = 0; i < tempDays.length; i++) {
                daysToAdd.append(tempDays[i].toString());

                if (i < tempDays.length - 1) {
                    daysToAdd.append(", ");
                }
            }

            statement.setString(3, daysToAdd.toString());
            statement.setString(4, currentSection.getTime().toString());
            statement.setString(5, currentSection.getLocation());
            statement.setString(6, Integer.toString(currentSection.getAvailable()));
            statement.setString(7, Integer.toString(currentSection.getCAP()));
            statement.setString(8, currentSection.getAssociatedCourse());
            statement.setString(9, currentSection.getAssociatedCategory());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * updateSection
     * update a section in the database
     * @param currentSection - section to be updated
     */
    @Override
    public void updateSection(Section currentSection) {
        try (final Connection newConnection = connection()) {

            // new Section(sectionID, instructor, days, times, location, available, capacity, associatedCourse, associatedCategory);
            final PreparedStatement statement = newConnection.prepareStatement("UPDATE SECTIONS SET " +
                    "INSTRUCTOR = ?, " +
                    "DAYS = ?, " +
                    "TIME = ?, " +
                    "LOCATION = ?  " +
                    "AVAILABLE = ?, " +
                    "CAPACITY = ?, " +
                    "COURSEID = ?, " +
                    "NAME = ?  " +
                    "WHERE SECTIONID = ?");
            statement.setString(1, currentSection.getInstructor());

            Section.availableSectionDays[] tempDays = currentSection.getDaysRaw();
            StringBuilder daysToAdd = new StringBuilder();

            for (int i = 0; i < tempDays.length; i++) {
                daysToAdd.append(tempDays[i].toString());

                if (i < tempDays.length - 1) {
                    daysToAdd.append(", ");
                }
            }

            statement.setString(2, daysToAdd.toString());
            statement.setString(3, currentSection.getTime().toString());
            statement.setString(4, String.valueOf(currentSection.getLocation()));
            statement.setString(5, String.valueOf(currentSection.getAvailable()));
            statement.setString(6, String.valueOf(currentSection.getCAP()));
            statement.setString(7, currentSection.getAssociatedCourse());
            statement.setString(8, currentSection.getAssociatedCategory());
            statement.setString(9, currentSection.getSection());

            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * deleteSection
     * deletes a section from the database
     * @param toRemove - section to be deleted
     */
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

    /**
     * getSection
     * get a section with a particular id from the database
     * @param sectionID - the section id of the section that we want
     * @return - a section with the id that we requested
     */
    @Override
    public Section getSection(String sectionID) {
        Section toReturn = null;
        try (Connection newConnection = connection())
        {
            final PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM SECTIONS WHERE SECTIONID = ?");
            statement.setString(1, sectionID);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                toReturn = fromResultSet(rs);
            }
            rs.close();
            statement.close();
        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }
        return toReturn;
    }
}
