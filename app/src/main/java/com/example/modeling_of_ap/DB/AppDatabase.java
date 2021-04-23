package com.example.modeling_of_ap.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Foods.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodsDao foodsDao();
}