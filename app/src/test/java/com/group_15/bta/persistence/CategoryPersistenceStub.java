package com.group_15.bta.persistence;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

public class CategoryPersistenceStub implements CategoryPersistence {
    private ArrayList<Category> categories;

    public CategoryPersistenceStub() {
        this.categories = new ArrayList<>();
        categories.add(new Category("Computer Science"));
        categories.add(new Category("Biological Sciences"));
        categories.add(new Category("Chemistry"));
        categories.add(new Category("English"));
        categories.add(new Category("Mathematics"));
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