package com.example.modeling_of_ap;

import androidx.annotation.NonNull;

public class Calculate{
    private int id;
    private String name;
    private double amount;
    private double calories;
    private double proteins;
    private double carbohydrates;
    private double fats;

    public Calculate(int id, double amount, String name, double calories, double proteins, double carbohydrates, double fats){
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.calories = calories*(amount/100);
        this.proteins = proteins*(amount/100);
        this.carbohydrates = carbohydrates*(amount/100);
        this.fats = fats*(amount/100);
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getAmount() { return amount; }

    public double getCalories() { return calories; }

    public double getProteins() { return proteins; }

    public double getCarbohydrates() { return carbohydrates; }

    public double getFats() { return fats; }

    @Override
    public String toString() {
        return "Калории = " + calories +
                ", Белки = " + proteins +
                ", Углеводы = " + carbohydrates +
                ", Жиры = " + fats +
                '}';
    }
}
