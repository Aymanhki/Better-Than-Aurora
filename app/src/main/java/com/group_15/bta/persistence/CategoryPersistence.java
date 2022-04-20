package com.group_15.bta.persistence;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

public interface CategoryPersistence {

    ArrayList<Category> getCategoryList();
    void insertCategory(Category currentCategory);
    void deleteCategory(Category toRemove);
}

