package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CoursePersistence;

import java.io.Serializable;
import java.math.BigDecimal;
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

    public Course fromResultSet(final ResultSet rs) throws SQLException {
        final String COURSEID = rs.getString("COURSEID");
        final String COURSE_NAME = rs.getString("TITLE");
        final String COURSE_DESCRIPTION = rs.getString("DESCRIPTION");
        final String CATEGORY = rs.getString("NAME");
        final int CREDIT = rs.getInt("CREDIT");
        final double TUITION = rs.getDouble("TUITION");
        final String ASSOCIATED_DEGREE = rs.getString("ASSOCIATEDDEGREE");
        return new Course(COURSEID, COURSE_NAME, COURSE_DESCRIPTION, CREDIT, CATEGORY, TUITION, ASSOCIATED_DEGREE);
    }

    public static Course fromResultSetStatic(final ResultSet rs) throws SQLException {
        final String COURSEID = rs.getString("COURSEID");
        final String COURSE_NAME = rs.getString("TITLE");
        final String COURSE_DESCRIPTION = rs.getString("DESCRIPTION");
        final String CATEGORY = rs.getString("NAME");
        final int CREDIT = rs.getInt("CREDIT");
        final double TUITION = rs.getDouble("TUITION");
        final String ASSOCIATED_DEGREE = rs.getString("ASSOCIATEDDEGREE");
        return new Course(COURSEID, COURSE_NAME, COURSE_DESCRIPTION, CREDIT, CATEGORY, TUITION, ASSOCIATED_DEGREE);
    }
    /**
     * makeSection
     * gets all the parameters from a resultSet
     * @return - a Section object with all the parameters from the database
     */
    public Section makeSection(final ResultSet rs) throws SQLException {
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
     * getCourseList
     * gets all the courses in the database
     * @return - an arraylist with all the courses in the database
     */
    @Override
    public ArrayList<Course> getCourseList() {
        final ArrayList<Course> courses = new ArrayList<>();

        try (final Connection newConnection = connection()){

            final Statement newStatement = newConnection.createStatement();
            final ResultSet newResultSet = newStatement.executeQuery("SELECT * FROM COURSES");
            while (newResultSet.next()) {
                final Course course = fromResultSet(newResultSet);
                final PreparedStatement preparedStatement=newConnection.prepareStatement("SELECT * FROM SECTIONS WHERE COURSEID=?");
                preparedStatement.setString(1, newResultSet.getString("COURSEID"));
                final ResultSet getSectionSet=preparedStatement.executeQuery();
                while(getSectionSet.next())
                {
                    course.addSection(makeSection(getSectionSet));
                }

                courses.add(course);
                preparedStatement.close();
                getSectionSet.close();

            }

            newResultSet.close();
            newStatement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return courses;
    }


    /**
     * insertCourses
     * inserts a course into the database
     * @param currentCourse - course to be inserted
     */
    @Override
    public void insertCourses(Course currentCourse) {
        try (final Connection newConnection = connection()){

            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO COURSES VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, currentCourse.getID());
            statement.setString(2, currentCourse.getTitle());
            statement.setString(3, currentCourse.getDescription());
            statement.setInt(4, currentCourse.getCreditHours());
            statement.setString(5, currentCourse.getAssociatedCategory());
            statement.setBigDecimal(6, BigDecimal.valueOf(currentCourse.getTuition()));
            statement.setString(7, currentCourse.getAssociatedDegree());
            statement.executeUpdate();
            if( currentCourse.getSections() != null) {

                SectionPersistenceHSQLDB sectionInserter = new SectionPersistenceHSQLDB(newConnection);
                for (int i = 0; i < currentCourse.getSections().size(); i++) {
                    sectionInserter.insertSection(currentCourse.getSections().get(i));
                }
            }


            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * updateCourse
     * update a course in the database
     * @param currentCourse - course to be updated
     */
    @Override
    public void updateCourse(Course currentCourse) {
        try (final Connection newConnection = connection()){

            final PreparedStatement statement = newConnection.prepareStatement("UPDATE COURSES SET TITLE = ?, DESCRIPTION = ?, CREDIT = ?, NAME = ?, TUITION = ?, ASSOCIATEDDEGREE = ?  WHERE COURSEID = ?");
            statement.setString(1, currentCourse.getTitle());
            statement.setString(2, currentCourse.getDescription());
            statement.setString(3, String.valueOf(currentCourse.getCreditHours()));
            statement.setString(4, currentCourse.getAssociatedCategory());
            statement.setString(5, currentCourse.getID());
            statement.setBigDecimal(6, BigDecimal.valueOf(currentCourse.getTuition()));
            statement.setString(7, currentCourse.getAssociatedDegree());
            statement.executeUpdate();

            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * deleteCourses
     * deletes a course from the database
     * @param toRemove - course to be deleted
     */
    @Override
    public void deleteCourses(Course toRemove) {
        try (final Connection newConnection = connection())
        {

            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE COURSEID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM SECTIONS WHERE COURSEID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM COURSES WHERE COURSEID = ?");
            statement.setString(1, toRemove.getID());
            statement.executeUpdate();

            statement.close();
            newConnection.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    /**
     * getCategoryCourses
     * get courses that are associated with a particular category
     * @param catName - category name we want to get the courses for
     * @return - an arraylist of all the courses in the database associated with the category
     */
    @Override
    public ArrayList<Course> getCategoryCourses(String catName){
        final ArrayList<Course> courses = new ArrayList<>();
        try (final Connection c = connection()){

            final PreparedStatement st = c.prepareStatement("SELECT * FROM COURSES WHERE NAME = ?");
            st.setString(1,catName);
            final ResultSet rs = st.executeQuery();
            while(rs.next()){
                final Course record = fromResultSet(rs);
                final PreparedStatement preparedStatement=c.prepareStatement("SELECT * FROM SECTIONS WHERE COURSEID=?");
                preparedStatement.setString(1,record.getID());
                final ResultSet getSections=preparedStatement.executeQuery();
                while(getSections.next())
                {
                    record.addSection(makeSection(getSections));
                }

                courses.add(record);
                preparedStatement.close();
                getSections.close();
            }

            rs.close();
            st.close();
            c.close();
            return courses;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * getCourse
     * gets a course from the database
     * @param courseID - id of course we want
     * @return - null if course not found, course if found
     */
    @Override
    public Course getCourse(String courseID) {
        Course toReturn = null;
        try (final Connection newConnection = connection()){

            final PreparedStatement statement = newConnection.prepareStatement("SELECT * FROM COURSES WHERE COURSEID = ?");
            statement.setString(1, courseID);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                toReturn = fromResultSet(rs);
            }


            rs.close();
            statement.close();
            newConnection.close();
        }
        catch (final SQLException newException)
        {
            throw new PersistenceException(newException);
        }
        return toReturn;
    }


}
