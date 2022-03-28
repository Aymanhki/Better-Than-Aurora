package com.group_15.bta.persistence;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group_15.bta.business.AccessCategories;

import com.group_15.bta.objects.Category;

import static org.junit.Assert.assertTrue;

public class CategoryListTest implements CategoryPersistence {
    private ArrayList<Category> categories;

    public CategoryListTest() {
        this.categories = new ArrayList<>();

        categories.add(new Category("COMP3010"));
        categories.add(new Category("COMP3020"));
        categories.add(new Category("COMP3350"));
        categories.add(new Category("COMP3380"));
    }
    @Override
    public ArrayList<Category> getCategoryList() {
        return categories;
    }

    @Override
    public void insertCategory(Category currentCategory) {
        // don't bother checking for duplicates
        categories.add(currentCategory);
    }

    @Override
    public void deleteCategory(Category currentCategory) {
        int index;

        index = categories.indexOf(currentCategory);
        if (index >= 0)
        {
            categories.remove(index);
        }
    }
}