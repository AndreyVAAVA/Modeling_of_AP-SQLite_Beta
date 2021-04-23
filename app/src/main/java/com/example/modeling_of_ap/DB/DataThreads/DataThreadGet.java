package com.example.modeling_of_ap.DB.DataThreads;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;

import java.util.ArrayList;
import java.util.List;

public class DataThreadGet extends Thread{
    private List<Foods> list = new ArrayList<>();
    private List<Foods> favouriteList = new ArrayList<>();
    private Foods food;
    private String foodName;
    private String ticker;
    @Override
    public void run() {
        AppDatabase db = App.getInstance().getDatabase();
        FoodsDao foodsDao = db.foodsDao();
        list = foodsDao.getAllFoods();
        if (foodName != null)
            food = foodsDao.getFood(foodName);
    }
    public List<Foods> getList(){
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Foods getFood(String foodName){
        this.foodName = foodName;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return food;
    }
    public Foods getCompanyByTicker(String ticker){
        this.ticker = ticker;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return food;
    }

}
