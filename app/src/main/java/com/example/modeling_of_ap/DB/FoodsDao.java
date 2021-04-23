package com.example.modeling_of_ap.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface FoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Foods foods);

    @Insert
    void insertAll(Foods... foods);

    @Update
    void update(Foods foods);

    @Delete
    void delete(Foods foods);

    // Получение всех Person из бд
    @Query("SELECT * FROM foods")
    List<Foods> getAllFoods();

    @Query("SELECT * FROM foods WHERE name LIKE :state")
    Foods getFood(String state);

    // Получение всех Person из бд с условием
    @Query("SELECT * FROM foods WHERE name LIKE :state")
    List<Foods> getAllFavoriteStocks(String state);
}
