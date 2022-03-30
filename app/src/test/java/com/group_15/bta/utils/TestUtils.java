package com.group_15.bta.utils;

import com.google.common.io.Files;

import com.group_15.bta.application.Main;

import java.io.File;
import java.io.IOException;


public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/Data.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
