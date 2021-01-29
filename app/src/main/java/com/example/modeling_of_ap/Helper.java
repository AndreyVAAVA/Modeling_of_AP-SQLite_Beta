package com.example.modeling_of_ap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Helper extends SQLiteOpenHelper {
    public static final String FOOD_TYPES_TABLE = "FoodTypes_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_CALORIES = "FOOD_CALORIES";
    public static final String COLUMN_FOOD_PROTEINS = "FOOD_PROTEINS";
    public static final String COLUMN_FOOD_CARBOHYDRATES = "FOOD_CARBOHYDRATES";
    public static final String COLUMN_FOOD_FATS = "FOOD_FATS";

    public Helper(@Nullable Context context) {
        super(context, "fooddata.db", null, 1);
    }

    public boolean setAmountToDB(Everything everything) {
        //MongoClient db = MongoClients.create();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FOOD_NAME, everything.getFood_name());
        cv.put(COLUMN_FOOD_CALORIES, everything.getCalories());
        cv.put(COLUMN_FOOD_PROTEINS, everything.getProteins());
        cv.put(COLUMN_FOOD_CARBOHYDRATES, everything.getCarbohydrates());
        cv.put(COLUMN_FOOD_FATS, everything.getFats());
        long insert = db.insert(FOOD_TYPES_TABLE, null, cv);
        if (insert == - 1){
            return false;
        } else {
            return true;
        }
    }

    public List<Everything> getEverything(){
        List<Everything> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FOOD_TYPES_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new food Objects. Put them into returnList.
            do {
                int foodID = cursor.getInt(0);
                String foodName = cursor.getString(1);
                int foodCalories = cursor.getInt(2);
                int foodProteins = cursor.getInt(3);
                int foodCarbohydrates = cursor.getInt(4);
                int foodFats = cursor.getInt(5);

                Everything newEverything = new Everything(foodID, foodName, foodCalories, foodProteins, foodCarbohydrates, foodFats);
                returnList.add(newEverything);

            } while (cursor.moveToNext());

        } else {
            showToast(DataBased.getAppContext(), "Ошибка");
        }

        // close database and cursor
        cursor.close();
        db.close();

        return returnList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatеment = "CREATE TABLE " + FOOD_TYPES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FOOD_NAME + " TEXT, " + COLUMN_FOOD_CALORIES + " INT, " + COLUMN_FOOD_PROTEINS + " INT, " + COLUMN_FOOD_CARBOHYDRATES + " INT, " + COLUMN_FOOD_FATS + " INT)";
        db.execSQL(createTableStatеment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean deleteFood(Everything everything) {
        //find food in the db. If it found, delete and return true, if it's not, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + FOOD_TYPES_TABLE + " WHERE " + COLUMN_ID + " = " + everything.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
