package com.example.modeling_of_ap.DBTotal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface EatedDao {

    @Insert
    void insert(Eated eated);

    @Insert
    void insertAll(Eated... eateds);

    @Update
    void update(Eated eated);

    @Delete
    void delete(Eated eated);

    // Получение всех Person из бд
    @Query("SELECT * FROM eated")
    List<Eated> getAllEated();

    @Query("SELECT * FROM eated WHERE name LIKE :state AND date LIKE :date AND weight LIKE :weight")
    Eated getEated(String state, String date, double weight);

    // Получение всех Person из бд с условием
    @Query("SELECT * FROM eated WHERE date LIKE :state")
    List<Eated> getAllEatedByDay(String state);
}
