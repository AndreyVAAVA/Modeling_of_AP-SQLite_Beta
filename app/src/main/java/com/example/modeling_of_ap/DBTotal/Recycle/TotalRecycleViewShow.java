package com.example.modeling_of_ap.DBTotal.Recycle;


public class TotalRecycleViewShow {
    private String name;
    private String date;
    private double weight;

    public TotalRecycleViewShow(String name, String date, double weight) {
        this.name = name;
        this.date = date;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
