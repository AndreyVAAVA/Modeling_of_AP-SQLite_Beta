package com.example.modeling_of_ap.DBTotal.DataThreads;

import android.util.Log;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.FoodsDao;
import com.example.modeling_of_ap.DBTotal.EatedDao;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

import static android.content.ContentValues.TAG;

public class TotalDataThreadInsert extends Thread{
    @Override
    public void run() {
        TotalDatabase db = App.getInstance().getTotalDatabase();
        EatedDao eatedDao = db.eatedDao();
        TotalDataThread thread = new TotalDataThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "DataThread, exception in DataThreadInsert, line 15");
        }
        /*thread.getStocksArrayList().forEach(stocksDao::insert);*/
    }
}
