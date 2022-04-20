package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.persistence.DegreePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DegreePersistenceHSQLDB implements DegreePersistence {
    private final String dbPath;

    public DegreePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }


    private Connection connection() throws SQLException {
        Connection toReturn;

        toReturn = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");

        return toReturn;
    }
    public Degree fromResultSet(final ResultSet rs) throws SQLException {
        return new Degree(rs.getString("NAME"));
    }

    @Override
    public void insert(Degree newDegree) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO DEGREES VALUES(?)");
            statement.setString(1, newDegree.getName());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public boolean contains(Degree newDegree) {
        Degree degree = null;
        try (Connection newConnection = connection()) {

            final PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM DEGREES WHERE NAME = ?");
            statement.setString(1, newDegree.getName());
            final ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                degree = fromResultSet(resultSet);
            }

            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return degree != null;
    }

    @Override
    public ArrayList<Degree> getDegreesList() {
        final ArrayList<Degree> degrees = new ArrayList<>();
        try (Connection newConnection = connection()) {
            final Statement statement = newConnection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM DEGREES");

            while (resultSet.next()) {
                degrees.add(fromResultSet(resultSet));
            }

            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return degrees;
    }

    @Override
    public ArrayList<String> getDegreeListNames() {
        ArrayList<Degree> degrees = getDegreesList();
        ArrayList<String> degreesNames = new ArrayList<>();
        for(int i=0; i<degrees.size(); i++)
        {
            degreesNames.add(degrees.get(i).getName());
        }
        return degreesNames;
    }

    @Override
    public ArrayList<Course> getDegreeCourses(String newDegree) {
        ArrayList<Course> toReturn = new ArrayList<>();
        try (Connection newConnection = connection()) {

            final Statement statement = newConnection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSES");
            CoursePersistenceHSQLDB instance = new CoursePersistenceHSQLDB(newConnection);
            while (resultSet.next()) {
                Course course = instance.fromResultSet(resultSet);
                if(course.associatedWithDegree(newDegree))
                toReturn.add(course);
            }


        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }
}
