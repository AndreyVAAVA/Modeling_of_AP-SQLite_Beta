package com.example.modeling_of_ap.DB.DataThreads;

import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;

// TODO Redevelop, because it doesn't work properly (not only this class, but classes that connected with inserts and updates mostly(i place todo there just to not forget to finish it))
public class DataThreadDelete extends Thread {
    Foods foods;
    @Override
    public void run() {
        AppDatabase db = App.getInstance().getDatabase();
        FoodsDao foodsDao = db.foodsDao();
        if (foods != null)
            foodsDao.delete(foods);
    }
/*    private void deleteAll(StocksDao stocksDao){
        DataThread thread = new DataThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "DataThread, exception in DataThreadUpdate, line 15");
        }
        thread.getStocksArrayList().forEach(stocksDao::update);
    }*/

    /**
     * Run this before thread.start();(or before thread.run())
     * @param foods
     */
    public void deleteOne(Foods foods){
        this.foods = foods;
        this.start();

    }
}
