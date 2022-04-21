package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;

import java.util.ArrayList;

/**
 * AccessCategories
 * Class to access categories in database.
 */
public class AccessCategories implements CategoryPersistence {

    //instance of category database
    CategoryPersistence categoryPersistence;

    //constructor, to start/set database
    public AccessCategories() {
        categoryPersistence = Services.getCategoryPersistence();
    }

    //constructor, to set database when it has already been started
    public AccessCategories(final CategoryPersistence categoryPersistence) {
        this();
        this.categoryPersistence = categoryPersistence;
    }

    /**
     * getCategoryList
     * returns the categories that are in the database
     * @return an arraylist of the categories in the database
     */
    public ArrayList<Category> getCategoryList() {
        return categoryPersistence.getCategoryList();
    }

    /**
     * insertCategory
     * inserts a category into the database
     * @param currentCategory - category to be inserted
     */
    public void insertCategory(Category currentCategory) {
        categoryPersistence.insertCategory(currentCategory);
    }

    /**
     * deleteCategory
     * deletes a category from the database
     * @param toRemove - category to be deleted
     */
    public void deleteCategory(Category toRemove) {
        categoryPersistence.deleteCategory(toRemove);
    }
}
