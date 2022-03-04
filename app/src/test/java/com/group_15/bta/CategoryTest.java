package com.group_15.bta;

import org.junit.Test;

import com.group_15.bta.objects.Category;

import static org.junit.Assert.*;

public class CategoryTest {
    @Test
    public void test(){
        Category cat;

        System.out.println("\nStarting Category Test");

        cat = new Category("Test Category");
        assertNotNull(cat);
        assertTrue("Test Category".equals(cat.getName()));

        System.out.println("\nDone Category Test");
    }
}
