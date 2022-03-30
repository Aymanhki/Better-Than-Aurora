package com.group_15.bta.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.group_15.bta.business.AccessCategories;
import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;
import com.group_15.bta.persistence.HSQLDB.CategoryPersistenceHSQLDB;
import com.group_15.bta.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        assertTrue("Computer Science".equals(category.getName()));

        System.out.println("Finished test AccessCategories");
    }

    @Test
    public void testGetCategories() {
        final ArrayList<Category> courses = accessCategories.getCategoryList();
        assertEquals(1, courses.size());
    }
    /*
            @Test
            public void testDeleteCategory() {
                final Category c = accessCategories.getCategoryList().get(0);
                ArrayList<Category> categories = accessCategories.getCategoryList();
                assertEquals(1, categories.size());
                accessCategories.deleteCategory(c);
                categories = accessCategories.getCategoryList();
                assertEquals(0, categories.size());
            }
    */
    @Test
    public void testInsertCategory() {
        final Category c = new Category("Art");
        accessCategories.insertCategory(c);
        assertEquals(2, accessCategories.getCategoryList().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
