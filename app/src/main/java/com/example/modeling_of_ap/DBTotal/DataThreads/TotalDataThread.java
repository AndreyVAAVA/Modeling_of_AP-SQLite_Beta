package com.example.modeling_of_ap.DBTotal.DataThreads;

import android.util.Log;

import com.example.modeling_of_ap.App;
import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.FoodsDao;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.EatedDao;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class TotalDataThread extends Thread{
    private String name;
    private double weight;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double sugars;
    private Eated eated;

    @Override
    public void run() {
        TotalDatabase db = App.getInstance().getTotalDatabase();
        EatedDao eatedDao = db.eatedDao();
        eated = new Eated();
        eated.setName(name);
        eated.setDate(LocalDate.now().toString());
        eated.setProteins(proteins);
        eated.setWeight(weight);
        eated.setCalories(calories);
        eated.setFats(fats);
        eated.setCarbohydrates(carbohydrates);
        eated.setSugars(sugars);
        eatedDao.insert(eated);
    }
    public void setAllData(String name, double weight, double calories, double proteins, double fats, double carbohydrates, double sugars){
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.start();
    }

    public Eated getEated() {
        return eated;
    }
}
