package com.group_15.bta;

import java.util.ArrayList;

public class CategoryListData implements CategoryList {
    private static final CategoryListData ourInstance = new CategoryListData();
    public ArrayList<Category> categories = new ArrayList<>();
    private CategoryListData () {
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

    public static CategoryListData getInstance(){
        return ourInstance;
    }

    public Category getCategory(int position){
        return categories.get(position);
    }

    public ArrayList<Category> getCategoryList(){
        return this.categories;
    }

    public void insertCategory(Category currentCategory){
        categories.add(currentCategory);
    }

    public void deleteCategory(int position){
        categories.remove(position);
    }
}
