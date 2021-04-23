package com.example.modeling_of_ap.DBTotal.DataThreads;


import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.EatedDao;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

import java.util.ArrayList;
import java.util.List;

public class TotalDataThreadGet extends Thread{
    private List<Eated> list = new ArrayList<>();
    private List<Eated> dayList = new ArrayList<>();
    private Eated eated;
    private String foodName;
    private String date;
    private double weight;
    @Override
    public void run() {
        TotalDatabase db = App.getInstance().getTotalDatabase();
        EatedDao eatedDao = db.eatedDao();
        list = eatedDao.getAllEated();
        if (foodName != null)
            eated = eatedDao.getEated(foodName, date, weight);
    }
    public List<Eated> getList(){
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Eated getEated(String foodName, String date, double weight){
        this.foodName = foodName;
        this.date = date;
        this.weight = weight;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return eated;
    }

}
