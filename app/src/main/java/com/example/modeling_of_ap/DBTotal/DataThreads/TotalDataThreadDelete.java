package com.example.modeling_of_ap.DBTotal.DataThreads;

import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.EatedDao;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

// TODO Redevelop, because it doesn't work properly (not only this class, but classes that connected with inserts and updates mostly(i place todo there just to not forget to finish it))
public class TotalDataThreadDelete extends Thread {
    Eated eated;
    @Override
    public void run() {
        TotalDatabase db = App.getInstance().getTotalDatabase();
        EatedDao eatedDao = db.eatedDao();
        if (eated != null)
            eatedDao.delete(eated);
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
     * @param eated
     */
    public void deleteOne(Eated eated){
        this.eated = eated;
        this.start();

    }
}
