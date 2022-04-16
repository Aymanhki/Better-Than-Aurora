package com.group_15.bta.persistence.HSQLDB;

import com.github.mikephil.charting.data.PieEntry;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
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

    public Student fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("STUDENTID");
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");
        final String associatedDegree = rs.getString("ASSOCIATEDDEGREE");
        return new Student(userName, password, name, associatedDegree);
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
            st.setString(1, currentStudent.getID());

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

            final PreparedStatement statement = newConnection.prepareStatement("UPDATE STUDENTS SET PASSWORD = ?, NAME = ?, ASSOCIATEDDEGREE = ?  WHERE STUDENTID = ?");
            statement.setString(1, currentStudent.getPassword());
            statement.setString(2, currentStudent.getName());
            statement.setString(3, currentStudent.getAssociatedDegree());
            statement.setString(4, currentStudent.getID());
            statement.executeUpdate();
            newConnection.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }


    @Override
    public void insertStudent(Student currentStudent) {

        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
            statement.setString(1, currentStudent.getPassword());
            statement.setString(2, currentStudent.getID());
            statement.setString(3, currentStudent.getName());
            statement.setString(4, currentStudent.getAssociatedDegree());
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
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();

            newConnection.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
    @Override
    public void deleteStudentID(String toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            statement.setString(1, toRemove);
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID = ?");
            statement.setString(1, toRemove);
            statement.executeUpdate();

            newConnection.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    public int getStudentDegreeInProgressCredit(Student student)
    {
        int sumRequiredInProgressCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "In Progress");
            statement.setString(3, "%, "+studentDegree+"%");
            statement.setString(4, "%"+studentDegree+", %");
            statement.setString(5, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredInProgressCourses += rs.getInt("CREDIT");
            }

            newConnection.close();
            rs.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredInProgressCourses;
    }


    public int getStudentDegreeTakenCredit(Student student)
    {
        int sumRequiredTakenCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ? AND GRADE != ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "In Progress");
            statement.setString(3, "%, "+studentDegree+"%");
            statement.setString(4, "%"+studentDegree+", %");
            statement.setString(5, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredTakenCourses += rs.getInt("CREDIT");
            }

            newConnection.close();
            rs.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredTakenCourses;
    }

    public int getStudentDegreeNotTakenCredit(Student student)
    {
        int sumRequiredNotTakenCourses = 0;
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT CREDIT FROM COURSES WHERE COURSEID NOT IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");
            statement.setString(1, student.getID());
            statement.setString(2, "%, "+studentDegree+"%");
            statement.setString(3, "%"+studentDegree+", %");
            statement.setString(4, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                sumRequiredNotTakenCourses += rs.getInt("CREDIT");
            }
            newConnection.close();
            rs.close();
            statement.close();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return sumRequiredNotTakenCourses;
    }

    @Override
    public ArrayList<PieEntry> getDegreeCreditBreakDown(Student student)
    {
        ArrayList<PieEntry> degreeBreakDown = new ArrayList();
        degreeBreakDown.add(new PieEntry(getStudentDegreeTakenCredit(student), "Complete"));
        degreeBreakDown.add(new PieEntry(getStudentDegreeInProgressCredit(student), "In Progress"));
        degreeBreakDown.add(new PieEntry(getStudentDegreeNotTakenCredit(student), "Unfulfilled"));
        return degreeBreakDown;
    }

    @Override
    public ArrayList<Course> getStudentDegreeNotTakenCourses(Student student)
    {
        ArrayList<Course> nonTakenCourses = new ArrayList();
        String studentDegree = student.getAssociatedDegree();
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM COURSES WHERE COURSEID NOT IN (SELECT COURSEID FROM STUDENTSECTIONS WHERE STUDENTID = ?) AND (ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ? OR ASSOCIATEDDEGREE LIKE ?)");

            statement.setString(1, student.getID());
            statement.setString(2, "%, "+studentDegree+"%");
            statement.setString(3, "%"+studentDegree+", %");
            statement.setString(4, "%"+studentDegree+"%");
            ResultSet rs = statement.executeQuery();

            SectionPersistenceHSQLDB sectionsGetter = new SectionPersistenceHSQLDB(newConnection);
            ArrayList<Section> sections = sectionsGetter.getSectionList();

            while (rs.next()) {
                final Course course = CoursePersistenceHSQLDB.fromResultSetStatic(rs);


                for (int i = 0; i < sections.size(); i++) {
                    if (sections.get(i).getAssociatedCourse().equals(course.getID())) {
                        course.addSection(sections.get(i));
                    }
                }

                nonTakenCourses.add(course);
            }


            newConnection.close();
            rs.close();
            statement.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return nonTakenCourses;
    }

    @Override
    public StudentSection getEnrolledSection(Student student, Section section) {
        StudentSection toReturn = null;

        try(Connection newConnection = connection())
        {
            PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM STUDENTSECTIONS WHERE STUDENTID = ? AND SECTIONID = ?");
            statement.setString(1, student.getID());
            statement.setString(2, section.getSection());
            ResultSet rs = statement.executeQuery();
            StudentSectionPersistenceHSQLDB parser = new StudentSectionPersistenceHSQLDB(newConnection);
            if(rs.next())
            {
                toReturn = parser.fromResultSet(rs);
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
