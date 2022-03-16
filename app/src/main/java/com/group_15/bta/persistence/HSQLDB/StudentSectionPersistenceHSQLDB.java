package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentSectionPersistenceHSQLDB implements StudentSectionPersistence {

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

    private StudentSection fromResultSet(final ResultSet rs) throws SQLException {
        final String studentID = rs.getString("STUDENTID");
        final String grade = rs.getString("GRADE");
        final String sectionID = rs.getString("SECTIONID");
        Section section = null;
        SectionPersistenceHSQLDB sectionGetter = new SectionPersistenceHSQLDB(connection());
        ArrayList<Section> availableSections = sectionGetter.getSectionList();
        for (int i = 0; i < availableSections.size(); i++) {
            if (availableSections.get(i).getSection().equals(sectionID)) {
                section = availableSections.get(i);
            }
        }
        return new StudentSection(studentID, grade, section);

    }

    @Override
    public ArrayList<StudentSection> getSectionList() {

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


    @Override
    public void insertSection(StudentSection currentSection) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTSECTIONS VALUES(?, ?, ?)");
            statement.setString(1, currentSection.getAssociatedStudent());
            statement.setString(2, currentSection.getGrade());
            statement.setString(2, currentSection.getSection().getSection());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteSection(StudentSection toRemove) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = ? AND SECTIONID = ?");
            statement.setString(1, toRemove.getAssociatedStudent());
            statement.setString(2, toRemove.getGrade());
            statement.setString(3, toRemove.getSection().getSection());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
