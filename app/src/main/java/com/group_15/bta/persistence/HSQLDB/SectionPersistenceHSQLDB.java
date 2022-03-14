package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionPersistenceHSQLDB implements SectionPersistence {

    private String dbPath;

    public SectionPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public ArrayList<Section> getSectionList() {
        return null;
    }

    @Override
    public Section getSection(int position) {
        return null;
    }

    @Override
    public void insertSection(Section currentSection) {

    }

    @Override
    public void deleteSection(int position) {

    }
}
