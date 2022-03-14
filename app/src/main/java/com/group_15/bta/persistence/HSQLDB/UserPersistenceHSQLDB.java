package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.business.LogInHandler;
import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserPersistenceHSQLDB implements UserPersistence {

    private String dbPath;
    private final String STUDENT_ACCOUNT_TYPE = "Student";
    private final String ADMIN_ACCOUNT_TYPE = "Administrator";
    private final String ADVISOR_ACCOUNT_TYPE = "Advisor";
    private final String INSTRUCTOR_ACCOUNT_TYPE = "Instructor";

    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs, String userType) throws SQLException {

        User toReturn = null;
        final String userName;
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");

        if (userType.equals(STUDENT_ACCOUNT_TYPE)) {
            userName = rs.getString("STUDENTID");
            toReturn = new Student(userName, password, name);
        } else if (userType.equals(ADMIN_ACCOUNT_TYPE)) {
            userName = rs.getString("ADMINID");
            toReturn = new Administrator(userName, password, name);
        } else if (userType.equals(ADVISOR_ACCOUNT_TYPE)) {
            userName = rs.getString("ADVISORID");
            toReturn = new Advisor(userName, password, name);
        } else if (userType.equals(INSTRUCTOR_ACCOUNT_TYPE)) {
            userName = rs.getString("INSTRUCTORID");
            toReturn = new Instructor(userName, password, name);
        }

        return toReturn;
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> toReturn = new ArrayList<>();

        try (Connection newConnection = connection()) {
            final Statement statement = newConnection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");
            while (resultSet.next()) {
                toReturn.add(fromResultSet(resultSet, STUDENT_ACCOUNT_TYPE));
            }

            resultSet = statement.executeQuery("SELECT * FROM ADMINS");
            while (resultSet.next()) {
                toReturn.add(fromResultSet(resultSet, ADMIN_ACCOUNT_TYPE));
            }

            resultSet = statement.executeQuery("SELECT * FROM ADVISORS");
            while (resultSet.next()) {
                toReturn.add(fromResultSet(resultSet, ADVISOR_ACCOUNT_TYPE));
            }

            resultSet = statement.executeQuery("SELECT * FROM INSTRUCTORS");
            while (resultSet.next()) {
                toReturn.add(fromResultSet(resultSet, INSTRUCTOR_ACCOUNT_TYPE));
            }

            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }

    @Override
    public void insertUser(User newUser) {
        try (final Connection newConnection = connection()) {

            PreparedStatement statement = newConnection.prepareStatement("");

            if (newUser instanceof Student) {
                statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?)");
            } else if (newUser instanceof Administrator) {
                statement = newConnection.prepareStatement("INSERT INTO ADMINS VALUES(?, ?, ?)");
            } else if (newUser instanceof Advisor) {
                statement = newConnection.prepareStatement("INSERT INTO ADVISORS VALUES(?, ?, ?)");
            } else if (newUser instanceof Instructor) {
                statement = newConnection.prepareStatement("INSERT INTO INSTRUCTORS VALUES(?, ?, ?)");
            }


            statement.setString(1, newUser.getID());
            statement.setString(2, newUser.getPassword());
            statement.setString(2, newUser.getName());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteUser(User toRemove) {
        try (final Connection newConnection = connection()) {

            PreparedStatement statement = newConnection.prepareStatement("");

            if (toRemove instanceof Student) {
                statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID = ?");
            } else if (toRemove instanceof Administrator) {
                statement = newConnection.prepareStatement("DELETE FROM ADMINS WHERE STUDENTID = ?");
            } else if (toRemove instanceof Advisor) {
                statement = newConnection.prepareStatement("DELETE FROM ADVISORS WHERE STUDENTID = ?");
            } else if (toRemove instanceof Instructor) {
                statement = newConnection.prepareStatement("DELETE FROM INSTRUCTORS WHERE STUDENTID = ?");
            }

            statement.setString(1, toRemove.getID());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
