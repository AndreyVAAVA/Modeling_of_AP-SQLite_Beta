package com.example.modeling_of_ap.DBTotal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.EatedDao;

@Database(entities = {Eated.class}, version = 1)
public abstract class TotalDatabase extends RoomDatabase {
    public abstract EatedDao eatedDao();
}