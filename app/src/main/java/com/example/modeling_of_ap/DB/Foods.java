package com.example.modeling_of_ap.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Foods {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private double caloriesOn100G;

    private double proteinsOn100G;

    private double fatsOn100G;

    private double carbohydratesOn100G;

    private double sugarsOn100G;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesOn100G() {
        return caloriesOn100G;
    }

    public void setCaloriesOn100G(double caloriesOn100G) {
        this.caloriesOn100G = caloriesOn100G;
    }

    public double getProteinsOn100G() {
        return proteinsOn100G;
    }

    public void setProteinsOn100G(double proteinsOn100G) {
        this.proteinsOn100G = proteinsOn100G;
    }

    public double getFatsOn100G() {
        return fatsOn100G;
    }

    public void setFatsOn100G(double fatsOn100G) {
        this.fatsOn100G = fatsOn100G;
    }

    public double getCarbohydratesOn100G() {
        return carbohydratesOn100G;
    }

    public void setCarbohydratesOn100G(double carbohydratesOn100G) {
        this.carbohydratesOn100G = carbohydratesOn100G;
    }

    public double getSugarsOn100G() {
        return sugarsOn100G;
    }

    public void setSugarsOn100G(double sugarsOn100G) {
        this.sugarsOn100G = sugarsOn100G;
    }
}
