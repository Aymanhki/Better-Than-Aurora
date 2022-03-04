package com.group_15.bta.persistence;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

public interface ICategoryList {

        static CategoryListData getInstance() {
            return null;
        }

        ArrayList<Category> getCategoryList();

        Category getCategory(int position);

        void insertCategory(Category currentCategory);

        void deleteCategory(int position);
}

