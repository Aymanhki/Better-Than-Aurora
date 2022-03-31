package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccessCategoriesTest {
    private AccessCategories accessCategories;

    @Before
    public void setUp() {
        this.accessCategories = new AccessCategories(new CategoryPersistenceStub());
    }

    @Test
    public void test1()
    {
        ArrayList<Category> categories = accessCategories.getCategoryList();
        assertNotNull(categories);

        int currSize = categories.size();
        assertEquals("4", String.valueOf(currSize));

        accessCategories.insertCategory(new Category("COMP4000"));
        categories = accessCategories.getCategoryList();
        currSize = categories.size();
        assertEquals("5", String.valueOf(currSize));

        accessCategories.deleteCategory(categories.get(4));
        accessCategories.deleteCategory(categories.get(3));
        categories = accessCategories.getCategoryList();
        currSize = categories.size();
        assertEquals("3", String.valueOf(currSize));

    }
}
