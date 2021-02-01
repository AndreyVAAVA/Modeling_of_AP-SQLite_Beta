package com.example.modeling_of_ap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Everything {
    private int id;
    private String food_name;
    private double calories;
    private double proteins;
    private double carbohydrates;
    private double fats;


    public Everything(int id, String food_name, double calories, double proteins, double carbohydrates, double fats) {
        this.id = id;
        this.food_name = food_name.toLowerCase();
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    @Override
    public String toString() {
        return  food_name + ':' +'\n' +
                "Калории - " + calories +
                ", Белки - " + proteins +
                ", Углеводы - " + carbohydrates +
                ", Жиры - " + fats;
    }

    public int getId() { return id; }

    public String getFood_name() {
        return food_name;
    }

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFats() {
        return fats;
    }
}
