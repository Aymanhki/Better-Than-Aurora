package com.group_15.bta.persistence;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

public class CategoryPersistenceStub implements CategoryPersistence {
    private ArrayList<Category> categories;

    public CategoryPersistenceStub() {
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