package com.group_15.bta.persistence;

import android.content.Context;

import com.group_15.bta.objects.Category;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Category> BTACategories;

    public static ArrayList<Category> getBTACategories(Context context)
    {
        if(BTACategories == null)
        {
            BTACategories = DataGenerator.createCategories(context);
        }



        return BTACategories;
    }
}
