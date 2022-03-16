package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;

import java.util.ArrayList;

public class AccessCategories implements CategoryPersistence {
    private static final AccessCategories ourInstance = new AccessCategories();
    public ArrayList<Category> categories = new ArrayList<>();

    public AccessCategories() {
//        Category c = new Category("Computer Science");//This should be an array accessed in data
//        categories.add(c);
//        c = new Category("Engineering");
//        categories.add(c);
//        c = new Category("Education");
//        categories.add(c);
//        c = new Category("English");
//        categories.add(c);
//        c = new Category("French");
//        categories.add(c);
//        c = new Category("Kinesiology");
//        categories.add(c);
//        c = new Category("History");
//        categories.add(c);
//        c = new Category("Mathematics");
//        categories.add(c);
//        c = new Category("Slavic Studies");
//        categories.add(c);
//        c = new Category("Physics");
//        categories.add(c);
//        c = new Category("Yiddish");
//        categories.add(c);

        CategoryPersistence categoryPersistence = Services.getCategoryPersistence();
        categories = categoryPersistence.getCategoryList();

    }

    public static AccessCategories getInstance() {
        return ourInstance;
    }


    public ArrayList<Category> getCategoryList() {
        return this.categories;
    }

    public void insertCategory(Category currentCategory) {
        categories.add(currentCategory);
    }

    public void deleteCategory(Category toRemove) {
        categories.remove(toRemove);
    }
}
