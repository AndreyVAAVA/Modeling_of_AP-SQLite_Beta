package com.example.modeling_of_ap;

import android.app.Application;

import androidx.room.Room;

import com.example.modeling_of_ap.DB.AppDatabase;
import com.example.modeling_of_ap.DBTotal.TotalDatabase;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    private TotalDatabase totalDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "foods")
                .build();
        totalDatabase = Room.databaseBuilder(this, TotalDatabase.class, "eated")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public TotalDatabase getTotalDatabase() {
        return totalDatabase;
    }
}