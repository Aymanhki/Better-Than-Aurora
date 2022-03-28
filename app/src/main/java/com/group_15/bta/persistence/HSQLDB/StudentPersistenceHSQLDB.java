package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Student;
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
    private String dbPath;
    private Connection existingConnection = null;

    public StudentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    public StudentPersistenceHSQLDB(Connection newConnection) {
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

    private Student fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("STUDENTID");
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");
        return new Student(userName, password, name);
    }

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
            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return students;
    }
    @Override
    public ArrayList<Student> getStudent(Student currentStudent) {
        final ArrayList<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENTID = ?");
            st.setString(1, currentStudent.getStudentID());

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }

            rs.close();
            st.close();

            return students;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updateStudent(Student currentStudent) {
        try (final Connection newConnection = connection()) {

            final PreparedStatement statement = newConnection.prepareStatement("UPDATE STUDENTS SET PASSWORD = ?, NAME = ?  WHERE STUDENTID = ?");
            statement.setString(1, currentStudent.getPassword());
            statement.setString(2, currentStudent.getName());
            statement.setString(3, currentStudent.getStudentID());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }


    @Override
    public void insertStudent(Student currentStudent) {

        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?)");
            statement.setString(1, currentStudent.getStudentID());
            statement.setString(2, currentStudent.getPassword());
            statement.setString(3, currentStudent.getID());
            statement.executeUpdate();
           /* new AccessUsers().insertUser(currentStudent);
            StudentSectionPersistenceHSQLDB studentSectionInserter = new StudentSectionPersistenceHSQLDB(newConnection);
            ArrayList<StudentSection> sections = studentSectionInserter.getSectionList();
            for (int i = 0; i < sections.size(); i++) {
                if (sections.get(i).getAssociatedStudent().equals(currentStudent.getStudentID())) {
                    studentSectionInserter.insertSection(sections.get(i));
                }
            }*/

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteStudent(Student toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getStudentID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getStudentID());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

}
