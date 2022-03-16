package com.group_15.bta.application;

import com.group_15.bta.persistence.CategoryPersistence;
import com.group_15.bta.persistence.CoursePersistence;
import com.group_15.bta.persistence.HSQLDB.CategoryPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.CoursePersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.PersistenceException;
import com.group_15.bta.persistence.HSQLDB.SectionPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.StudentPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.StudentSectionPersistenceHSQLDB;
import com.group_15.bta.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.group_15.bta.persistence.SectionPersistence;
import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.persistence.StudentSectionPersistence;
import com.group_15.bta.persistence.UserPersistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class Services implements Serializable {
    private static StudentPersistence studentPersistence = null;
    private static CoursePersistence coursePersistence = null;
    private static SectionPersistence sectionPersistence = null;
    private static CategoryPersistence categoryPersistence = null;
    private static UserPersistence userPersistence = null;
    private static StudentSectionPersistence studentSectionPersistence = null;
    private static Connection connection = null;

    public static synchronized StudentPersistence getStudentPersistence() {
        if (studentPersistence == null) {
            //studentPersistence = new StudentPersistenceStub();
            studentPersistence = new StudentPersistenceHSQLDB(Main.getDBPathName());
        }

        return studentPersistence;
    }

    public static synchronized CoursePersistence getCoursePersistence() {
        if (coursePersistence == null) {
            //coursePersistence = new CoursePersistenceStub();
            coursePersistence = new CoursePersistenceHSQLDB(Main.getDBPathName());
        }

        return coursePersistence;
    }

    public static synchronized SectionPersistence getSectionPersistence() {
        if (sectionPersistence == null) {
            //sectionPersistence = new SectionPersistenceStub();
            sectionPersistence = new SectionPersistenceHSQLDB(Main.getDBPathName());
        }

        return sectionPersistence;
    }

    public static synchronized CategoryPersistence getCategoryPersistence() {
        if (categoryPersistence == null) {
            //categoryPersistence = new CategoryPersistenceStub();
            categoryPersistence = new CategoryPersistenceHSQLDB(Main.getDBPathName());
        }

        return categoryPersistence;
    }

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            //userPersistence = new UserPersistenceStub();
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
        }

        return userPersistence;
    }

    public static synchronized StudentSectionPersistence getStudentSectionPersistence() {
        if (studentSectionPersistence == null) {
            //categoryPersistence = new StudentSectionPersistenceStub();
            studentSectionPersistence = new StudentSectionPersistenceHSQLDB(Main.getDBPathName());
        }

        return studentSectionPersistence;
    }

}
