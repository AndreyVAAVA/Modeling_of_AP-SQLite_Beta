package com.example.modeling_of_ap.DBTotal.DataThreads;

import android.util.Log;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.EatedDao;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

import static android.content.ContentValues.TAG;

public class TotalDataThreadUpdate extends Thread{
    Eated eated;
    @Override
    public void run() {
        TotalDatabase db = App.getInstance().getTotalDatabase();
        EatedDao eatedDao = db.eatedDao();
        if (eated != null)
            eatedDao.update(eated);
    }
    private void updateAll(EatedDao eatedDao){
        TotalDataThread thread = new TotalDataThread();
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
     * @param eated
     */
    public void updateOne(Eated eated){
        this.eated = eated;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
