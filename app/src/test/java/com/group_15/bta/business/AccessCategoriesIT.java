package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;
import com.group_15.bta.persistence.HSQLDB.CategoryPersistenceHSQLDB;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessCategoriesIT {
    private AccessCategories accessCategories;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final CategoryPersistence persistence = new CategoryPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessCategories = new AccessCategories(persistence);
    }

    @Test
    public void testListCategories() {
        final Category category;

        category = accessCategories.getCategoryList().get(0);
        assertNotNull("first sequential course should not be null", category);
        assertTrue("Biological Sciences".equals(category.getName()));

        System.out.println("Finished test AccessCategories");
    }

    @Test
    public void testGetCategories() {
        final ArrayList<Category> courses = accessCategories.getCategoryList();
        assertEquals(5, courses.size());
    }

    @Test
    public void testInsertCategory() {
        final Category c = new Category("Art");
        accessCategories.insertCategory(c);
        assertEquals(6, accessCategories.getCategoryList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
