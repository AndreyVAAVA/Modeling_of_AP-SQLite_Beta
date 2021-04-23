package com.example.modeling_of_ap.DB.DataThreads;

import android.util.Log;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;

import static android.content.ContentValues.TAG;

public class DataThreadUpdate extends Thread{
    Foods foods;
    @Override
    public void run() {
        AppDatabase db = App.getInstance().getDatabase();
        FoodsDao foodsDao = db.foodsDao();
        if (foods != null)
            foodsDao.update(foods);
    }
    private void updateAll(FoodsDao stocksDao){
        DataThread thread = new DataThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "DataThread, exception in DataThreadUpdate, line 15");
        }
//        thread.getStocksArrayList().forEach(stocksDao::update);
    }

    /**
     * Run this before thread.start();(or before thread.run())
     * @param foods
     */
    public void updateOne(Foods foods){
        this.foods = foods;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
