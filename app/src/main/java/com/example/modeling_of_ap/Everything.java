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
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;


    public Everything(int id, String food_name, int calories, int proteins, int carbohydrates, int fats) {
        this.id = id;
        this.food_name = food_name.toLowerCase();
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "Everything{" +
                "id=" + id +
                ", food_name='" + food_name + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", fats=" + fats +
                '}';
    }

    public int getId() { return id; }

    public String getFood_name() {
        return food_name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getFats() {
        return fats;
    }
}
