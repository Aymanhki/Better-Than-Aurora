package com.group_15.bta;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;
import android.content.res.AssetManager;

import com.group_15.bta.application.Main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        CreateStudentTest.class,
        DeleteStudentTest.class,
        EditStudentTest.class,
        SearchStudentAdvisorTest.class,
        CreateCourseTest.class,
        DeleteCourseTest.class,
        UpdateGradeInstructorTest.class,
        DegreeProgressTest.class,
        ViewTuitionFeesTest.class,
        ViewGPATest.class,
        ViewTuitionFeesDeadlineTest.class,
        ViewPreviousCourseGradeTest.class,
        StudentCoursesDoNotOverlapTest.class,
        InformStudentNewCourseIsAvailableTest.class,
        LimitSectionRegistrationTest.class,
        StudentDropCourseTest.class,
        AdminDeleteCourseTest.class,
        StudentRegisterForCourseTest.class
})
public class AllTests
{

    public static boolean deleteFolder(File removableFolder) {
        File[] files = removableFolder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                boolean success;
                if (file.isDirectory())
                    success = deleteFolder(file);
                else success = file.delete();
                if (!success) return false;
            }
        }
        return removableFolder.delete();
    }

    protected static void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getApplicationContext().getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}



