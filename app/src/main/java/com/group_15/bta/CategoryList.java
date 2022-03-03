package com.group_15.bta;

import java.util.ArrayList;

public interface CategoryList {

        static CategoryListData getInstance() {
            return null;
        }

        ArrayList<Category> getCategoryList();

        Category getCategory(int position);

        void insertCategory(Category currentCategory);

        void deleteCategory(int position);
}

