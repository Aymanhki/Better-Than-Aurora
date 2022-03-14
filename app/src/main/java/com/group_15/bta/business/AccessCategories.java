package com.group_15.bta.business;

import com.group_15.bta.objects.Category;
import com.group_15.bta.persistence.CategoryPersistence;

import java.util.ArrayList;

public class AccessCategories implements CategoryPersistence {
    private static final AccessCategories ourInstance = new AccessCategories();
    public ArrayList<Category> categories = new ArrayList<>();

    private AccessCategories() {
        Category c = new Category("Computer Science");//This should be an array accessed in data
        categories.add(c);
        c = new Category("Engineering");
        categories.add(c);
        c = new Category("Education");
        categories.add(c);
        c = new Category("English");
        categories.add(c);
        c = new Category("French");
        categories.add(c);
        c = new Category("Kinesiology");
        categories.add(c);
        c = new Category("History");
        categories.add(c);
        c = new Category("Mathematics");
        categories.add(c);
        c = new Category("Slavic Studies");
        categories.add(c);
        c = new Category("Physics");
        categories.add(c);
        c = new Category("Yiddish");
        categories.add(c);

    }

    public static AccessCategories getInstance(){
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
