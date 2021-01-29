package com.example.modeling_of_ap;

import androidx.annotation.NonNull;

public class Calculate implements Calories, Proteins, Сarbohydrates, Fats{
    private String name;
    private int amount;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;

    Calculate(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        calculate();
        return "Калории = " + calories*(amount/100) +
                ", Белки = " + proteins*(amount/100) +
                ", Углеводы = " + carbohydrates*(amount/100) +
                ", Жиры = " + fats*(amount/100) +
                '}';
    }

    @Override
    public int getAmountFromDBCalories(String name) {
        return 0;
    }

    @Override
    public void setAmountToDBCalories(double amount, String name) {

    }

    @Override
    public int getAmountFromDBFats(String name) {
        return 0;
    }

    @Override
    public void setAmountToDBFats(double amount, String name) {

    }

    @Override
    public int getAmountFromDBProteins(String name) {
        return 0;
    }

    @Override
    public void setAmountToDBProteins(double amount, String name) {

    }

    @Override
    public int getAmountFromDBСarbohydrates(String name) {
        return 0;
    }

    @Override
    public void setAmountToDBСarbohydrates(double amount, String name) {

    }

    public void calculate(){
        calories = getAmountFromDBCalories(name);
        proteins = getAmountFromDBProteins(name);
        carbohydrates = getAmountFromDBСarbohydrates(name);
        fats = getAmountFromDBFats(name);
    }
}
