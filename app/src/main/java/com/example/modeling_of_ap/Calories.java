package com.example.modeling_of_ap;

public interface Calories {
    public int getAmountFromDBCalories(String name);
    public void setAmountToDBCalories(double amount, String name);
}
