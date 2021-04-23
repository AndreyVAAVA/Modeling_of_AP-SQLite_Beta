package com.example.modeling_of_ap.DBTotal.DataThreads;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class TotalDataThreadBackground extends Thread{
    @Override
    public void run() {
        TotalDataThreadInsert threadInsert = new TotalDataThreadInsert();
        threadInsert.start();
        try {
            threadInsert.join();
        } catch (InterruptedException e) {
            Log.d(TAG, "Exception in DataThreadBackground. It could be because of changed StocksDAo or because of too much requests");
        }
    }
}
