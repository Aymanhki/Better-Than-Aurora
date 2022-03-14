package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryPersistenceHSQLDB implements CategoryPersistence {

    private String dbPath;

    public CategoryPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public ArrayList<Category> getCategoryList() {
        return null;
    }

    @Override
    public Category getCategory(int position) {
        return null;
    }

    @Override
    public void insertCategory(Category currentCategory) {

    }

    @Override
    public void deleteCategory(int position) {

    }
}
