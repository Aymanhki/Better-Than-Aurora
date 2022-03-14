package com.group_15.bta.application;

public class Main {
    private static String dbName = "Data";

    public static void main(String[] args) {
        //Ideally command line parser would go here.
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }
}
