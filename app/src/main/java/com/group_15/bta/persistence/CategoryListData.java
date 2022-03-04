package com.group_15.bta.persistence;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

/*
 * Class for List of categories for courses ("database")
 */

public class CategoryListData implements ICategoryList {

    private static final CategoryListData ourInstance = new CategoryListData(); //one instance
    public ArrayList<Category> categories = new ArrayList<>(); //list of categories

    //constructor
    private CategoryListData () {

        //hardcoded data into "database"
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

    //getters
    public static CategoryListData getInstance(){
        return ourInstance;
    }

    public Category getCategory(int position){
        return categories.get(position);
    }

    public ArrayList<Category> getCategoryList(){
        return this.categories;
    }

    //add category to list
    public void insertCategory(Category currentCategory){
        categories.add(currentCategory);
    }

    //delete category from list
    public void deleteCategory(int position){
        categories.remove(position);
    }
}
