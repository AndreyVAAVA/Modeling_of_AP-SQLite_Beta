package com.example.modeling_of_ap.DB.DataThreads;

import android.util.Log;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.FoodsDao;

import static android.content.ContentValues.TAG;

public class DataThreadInsert extends Thread{
    @Override
    public void run() {
        AppDatabase db = App.getInstance().getDatabase();
        FoodsDao stocksDao = db.foodsDao();
        DataThread thread = new DataThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "DataThread, exception in DataThreadInsert, line 15");
        }
        /*thread.getStocksArrayList().forEach(stocksDao::insert);*/
    }
}
