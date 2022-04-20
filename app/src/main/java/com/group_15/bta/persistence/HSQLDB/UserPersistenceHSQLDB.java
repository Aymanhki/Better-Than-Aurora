package com.group_15.bta.persistence.HSQLDB;

import android.content.Context;
import android.content.Intent;

import com.group_15.bta.objects.Administrator;
import com.group_15.bta.objects.Advisor;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.presentation.AdminMenuActivity;
import com.group_15.bta.presentation.AdvisorMenuActivity;
import com.group_15.bta.presentation.InstructorMenuActivity;
import com.group_15.bta.presentation.StudentAccountActivity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserPersistenceHSQLDB implements UserPersistence, Serializable {

    private final String dbPath;
    private User currentUser = null;
    private final String STUDENT_ACCOUNT_TYPE = "STUDENTS";
    private final String ADMIN_ACCOUNT_TYPE = "ADMINS";
    private final String ADVISOR_ACCOUNT_TYPE = "ADVISORS";
    private final String INSTRUCTOR_ACCOUNT_TYPE = "INSTRUCTORS";


    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }


    private Connection connection() throws SQLException {
        Connection toReturn;

        toReturn = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");

        return toReturn;
    }

    public User fromResultSet(final ResultSet rs, String userType) throws SQLException {

        User toReturn = null;
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");
        final String userName = rs.getString("USERNAME");

        switch (userType) {
            case STUDENT_ACCOUNT_TYPE:
                final String associatedDegree = rs.getString("ASSOCIATEDDEGREE");
                toReturn = new Student(userName, password, name, associatedDegree);
                break;
            case ADMIN_ACCOUNT_TYPE:
                toReturn = new Administrator(userName, password, name);
                break;
            case ADVISOR_ACCOUNT_TYPE:
                toReturn = new Advisor(userName, password, name);
                break;
            case INSTRUCTOR_ACCOUNT_TYPE:
                toReturn = new Instructor(userName, password, name);
                break;
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

            PreparedStatement statement = null;

            if (newUser instanceof Student) {
                statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
                statement.setString(4, ((Student)newUser).getAssociatedDegree());
            } else if (newUser instanceof Administrator) {
                statement = newConnection.prepareStatement("INSERT INTO ADMINS VALUES(?, ?, ?)");
            } else if (newUser instanceof Advisor) {
                statement = newConnection.prepareStatement("INSERT INTO ADVISORS VALUES(?, ?, ?)");
            } else if (newUser instanceof Instructor) {
                statement = newConnection.prepareStatement("INSERT INTO INSTRUCTORS VALUES(?, ?, ?)");
            }

            if (statement!=null) {
                statement.setString(1, newUser.getID());
                statement.setString(2, newUser.getPassword());
                statement.setString(3, newUser.getName());
                statement.executeUpdate();
            }
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteUser(User toRemove) {
        try (final Connection newConnection = connection()) {

            PreparedStatement statement = newConnection.prepareStatement("");

            if (toRemove instanceof Student) {
                statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE USERNAME = ?");
            } else if (toRemove instanceof Administrator) {
                statement = newConnection.prepareStatement("DELETE FROM ADMINS WHERE USERNAME = ?");
            } else if (toRemove instanceof Advisor) {
                statement = newConnection.prepareStatement("DELETE FROM ADVISORS WHERE USERNAME = ?");
            } else if (toRemove instanceof Instructor) {
                statement = newConnection.prepareStatement("DELETE FROM INSTRUCTORS WHERE USERNAME = ?");
            }

            statement.setString(1, toRemove.getID());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(String userName, String password) {
        currentUser = getUser(userName, password);
    }

    @Override
    public boolean validateLoginAttempt(String userName, String password)
    {
        boolean found;
        try(Connection connection = connection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT USERNAME, PASSWORD FROM (SELECT USERNAME, PASSWORD FROM STUDENTS UNION ALL SELECT USERNAME, PASSWORD FROM ADMINS UNION ALL SELECT USERNAME, PASSWORD FROM ADVISORS UNION ALL SELECT USERNAME, PASSWORD FROM INSTRUCTORS) WHERE USERNAME = ? AND PASSWORD = ?");
            statement.setString(1, userName);
            statement.setString(2, password);
            found = statement.executeQuery().next();
        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }
        return found;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class intendedActivity(String userName, String password) {
        Class toReturn = null;

        if(validateLoginAttempt(userName, password))
        {
            User loginAttempt = getUser(userName, password);

            if(loginAttempt instanceof Student)
            {
                toReturn = StudentAccountActivity.class;
            }
            else if(loginAttempt instanceof Administrator)
            {
                toReturn = AdminMenuActivity.class;
            }
            else if(loginAttempt instanceof Advisor)
            {
                toReturn = AdvisorMenuActivity.class;
            }
            else if(loginAttempt instanceof Instructor)
            {
                toReturn = InstructorMenuActivity.class;
            }
        }

        return toReturn;
    }

    @Override
    public Intent destinationIntent(String userName, String password, Context currentActivity) {
        Intent toReturn = new Intent(currentActivity, currentActivity.getClass());

        if(validateLoginAttempt(userName, password))
        {
            toReturn = new Intent(currentActivity, intendedActivity(userName, password));

        }

        return toReturn;
    }

    @Override
    public User getUser(String userName, String password)
    {

        User toReturn = null;

        try(Connection connection = connection())
        {
            String prepare = "SELECT * FROM "+getUserTable(userName, password)+" WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement statement = connection.prepareStatement(prepare);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                toReturn = fromResultSet(rs, rs.getMetaData().getTableName(1));
            }

        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }

        return toReturn;
    }

    private String getUserTable(String userName, String password)
    {
        String toReturn = null;

        try(Connection connection = connection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT USERNAME, PASSWORD, 'STUDENTS' AS SOURCE FROM STUDENTS WHERE USERNAME = ? AND PASSWORD = ? UNION ALL " +
                                                                        "SELECT USERNAME, PASSWORD, 'ADMINS' AS SOURCE FROM ADMINS WHERE USERNAME = ? AND PASSWORD = ? UNION ALL " +
                                                                        "SELECT USERNAME, PASSWORD, 'ADVISORS' AS SOURCE FROM ADVISORS WHERE USERNAME = ? AND PASSWORD = ? UNION ALL " +
                                                                        "SELECT USERNAME, PASSWORD, 'INSTRUCTORS' AS SOURCE FROM INSTRUCTORS WHERE USERNAME = ? AND PASSWORD = ?");
            statement.setString(1, userName);
            statement.setString(2, password);
            statement.setString(3, userName);
            statement.setString(4, password);
            statement.setString(5, userName);
            statement.setString(6, password);
            statement.setString(7, userName);
            statement.setString(8, password);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                toReturn = rs.getString("SOURCE").trim();
            }
        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }


        return toReturn;
    }
}
