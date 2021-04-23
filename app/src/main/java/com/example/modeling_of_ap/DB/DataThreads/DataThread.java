package com.example.modeling_of_ap.DB.DataThreads;

import android.util.Log;

import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class DataThread extends Thread{
    private String name;
    private double caloriesOn100G;
    private double proteinsOn100G;
    private double fatsOn100G;
    private double carbohydratesOn100G;
    private double sugarsOn100G;
    private Foods food;

    @Override
    public void run() {
        AppDatabase db = App.getInstance().getDatabase();
        FoodsDao foodsDao = db.foodsDao();
        food = new Foods();
        food.setName(name);
        food.setCaloriesOn100G(caloriesOn100G);
        food.setProteinsOn100G(proteinsOn100G);
        food.setFatsOn100G(fatsOn100G);
        food.setCarbohydratesOn100G(carbohydratesOn100G);
        food.setSugarsOn100G(sugarsOn100G);
        foodsDao.insert(food);
    }
    public void setAllData(String name, double caloriesOn100G, double proteinsOn100G, double fatsOn100G, double carbohydratesOn100G, double sugarsOn100G){
        this.name = name;
        this.caloriesOn100G = caloriesOn100G;
        this.proteinsOn100G = proteinsOn100G;
        this.fatsOn100G = fatsOn100G;
        this.carbohydratesOn100G = carbohydratesOn100G;
        this.sugarsOn100G = sugarsOn100G;
        this.start();
    }
    public Foods getFoodData() {
        return food;
    }
}
