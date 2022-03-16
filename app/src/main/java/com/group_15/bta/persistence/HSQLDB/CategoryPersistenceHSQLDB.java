package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.persistence.CategoryPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryPersistenceHSQLDB implements CategoryPersistence, Serializable {

    private String dbPath;
    private Connection existingConnection = null;

    public CategoryPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    public CategoryPersistenceHSQLDB(Connection newConnection) {
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

    private Category fromResultSet(final ResultSet rs) throws SQLException {
        final String category = rs.getString("NAME");
        return new Category(category);
    }

    @Override
    public ArrayList<Category> getCategoryList() {
        final ArrayList<Category> categories = new ArrayList<>();

        try (Connection newConnection = connection()) {
            final Statement statement = newConnection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIES");
            while (resultSet.next()) {
                final Category category = fromResultSet(resultSet);
                CoursePersistenceHSQLDB coursesGetter = new CoursePersistenceHSQLDB(newConnection);
                ArrayList<Courses> courses = coursesGetter.getCourseList();
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getAssociatedCategory().equals(category.getName())) {
                        category.addCourse(courses.get(i));
                    }
                }
                categories.add(category);
            }
            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return categories;
    }

    @Override
    public void insertCategory(Category currentCategory) {
        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO CATEGORIES VALUES(?)");
            statement.setString(1, currentCategory.getName());
            statement.executeUpdate();

            CoursePersistenceHSQLDB courseInserter = new CoursePersistenceHSQLDB(newConnection);
            for (int i = 0; i < currentCategory.getCourses().size(); i++) {
                courseInserter.insertCourses(currentCategory.getCourses().get(i));
            }

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }


    @Override
    public void deleteCategory(Category toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM SECTIONS WHERE NAME = ?");
            statement.setString(1, toRemove.getName());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM COURSES WHERE NAME = ?");
            statement.setString(1, toRemove.getName());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM CATEGORIES WHERE NAME = ?");
            statement.setString(1, toRemove.getName());
            statement.executeUpdate();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }
}
