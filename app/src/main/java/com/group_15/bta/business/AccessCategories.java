package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;

import java.util.ArrayList;

public class AccessCategories implements CategoryPersistence {
    CategoryPersistence categoryPersistence;

    public AccessCategories() {
        categoryPersistence = Services.getCategoryPersistence();
    }

    public AccessCategories(final CategoryPersistence categoryPersistence) {
        this();
        this.categoryPersistence = categoryPersistence;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryPersistence.getCategoryList();
    }

    public void insertCategory(Category currentCategory) {
        categoryPersistence.insertCategory(currentCategory);
    }

    public void deleteCategory(Category toRemove) {
        categoryPersistence.deleteCategory(toRemove);
    }
}
