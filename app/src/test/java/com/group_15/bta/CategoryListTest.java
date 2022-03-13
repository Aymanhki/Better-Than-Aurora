package com.group_15.bta;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.business.AccessCategories;

import com.group_15.bta.objects.Category;

import static org.junit.Assert.assertTrue;

public class CategoryListTest {
        private AccessCategories categoryList;
        private ArrayList<Category> categories;

        @Before
        public void setUp() {
            categoryList = AccessCategories.getInstance();
            categories = categoryList.getCategoryList();
        }

       @Test
    public void test1(){
            final Category category;

            System.out.println("\n Starting Category Test");
            Category c = new Category("Test Category");

            categoryList.insertCategory(c);

            categories = categoryList.getCategoryList();

            assertTrue(categories.contains(c));

            System.out.println("\n Finsihed Category List Test");
       }
}
