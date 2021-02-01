package com.example.modeling_of_ap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DayRatioHelper extends SQLiteOpenHelper {
    public static String DB_PATH;
    public static String DB_NAME = "eated.db";
    public static final String FOOD_TABLE = "Food_TABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOOD_AMOUNT = "FOOD_AMOUNT";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_CALORIES = "FOOD_CALORIES";
    public static final String COLUMN_FOOD_PROTEINS = "FOOD_PROTEINS";
    public static final String COLUMN_FOOD_CARBOHYDRATES = "FOOD_CARBOHYDRATES";
    public static final String COLUMN_FOOD_FATS = "FOOD_FATS";

    public DayRatioHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    public boolean setAmountToDB(Calculate calculate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FOOD_AMOUNT, calculate.getAmount());
        cv.put(COLUMN_FOOD_NAME, calculate.getName());
        cv.put(COLUMN_FOOD_CALORIES, calculate.getCalories());
        cv.put(COLUMN_FOOD_PROTEINS, calculate.getProteins());
        cv.put(COLUMN_FOOD_CARBOHYDRATES, calculate.getCarbohydrates());
        cv.put(COLUMN_FOOD_FATS, calculate.getFats());
        long insert = db.insert(FOOD_TABLE, null, cv);
        if (insert == - 1){
            return false;
        } else {
            return true;
        }
    }

    public List<Calculate> getEverything(){
        List<Calculate> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FOOD_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new food Objects. Put them into returnList.
            do {
                int foodID = cursor.getInt(0);
                double foodAmount = cursor.getDouble(1);
                String foodName = cursor.getString(2);
                double foodCalories = cursor.getDouble(3);
                double foodProteins = cursor.getDouble(4);
                double foodCarbohydrates = cursor.getDouble(5);
                double foodFats = cursor.getDouble(6);

                Calculate newCalculate = new Calculate(foodID, foodAmount, foodName, foodCalories, foodProteins, foodCarbohydrates, foodFats);
                returnList.add(newCalculate);

            } while (cursor.moveToNext());

        } else {
            showToast(DayRatio.getAppContext(), "Ошибка");
        }

        // close database and cursor
        cursor.close();
        db.close();

        return returnList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatеment = "CREATE TABLE " + FOOD_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FOOD_AMOUNT + " REAL, " + COLUMN_FOOD_NAME + " TEXT, " + COLUMN_FOOD_CALORIES + " REAL, " + COLUMN_FOOD_PROTEINS + " REAL, " + COLUMN_FOOD_CARBOHYDRATES + " REAL, " + COLUMN_FOOD_FATS + " REAL)";
        db.execSQL(createTableStatеment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean deleteFood(Calculate calculate) {
        //find food in the db. If it found, delete and return true, if it's not, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + FOOD_TABLE + " WHERE " + COLUMN_ID + " = " + calculate.getId();

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

    public SQLiteDatabase open()throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }

    public double sumFoodColumn(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_FOOD_PROTEINS + ") as Total FROM " + FOOD_TABLE, null);
        if (cursor.moveToFirst()) {
            double total = cursor.getDouble(cursor.getColumnIndex("Total"));// get final total
            return total;
        }
        return 0;
    }
}
