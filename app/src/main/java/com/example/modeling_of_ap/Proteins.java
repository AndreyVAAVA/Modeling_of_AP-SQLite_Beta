package com.example.modeling_of_ap;

public interface Proteins {
    public int getAmountFromDBProteins(String name);
    public void setAmountToDBProteins(double amount, String name);
}
