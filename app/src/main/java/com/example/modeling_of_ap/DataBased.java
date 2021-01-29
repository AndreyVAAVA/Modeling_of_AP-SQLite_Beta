package com.example.modeling_of_ap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.admin.DeviceAdminService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DataBased extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_name;
    private EditText editText_calories;
    private EditText editText_proteins;
    private EditText editText_carbohydrates;
    private EditText editText_fats;
    private String food_name;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private Everything stuff;
    private ListView listView;
    private static Context context;
    private Helper helper;
    private ArrayAdapter foodArrayAdapter;

    public DataBased() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_based);
        Button get_data = findViewById(R.id.getData);
        get_data.setOnClickListener(this);
        Button add_data = findViewById(R.id.add);
        add_data.setOnClickListener(this);
        Button deleteFromDB = findViewById(R.id.del);
        deleteFromDB.setOnClickListener(this);
        Button backToPrevious = findViewById(R.id.back);
        backToPrevious.setOnClickListener(this);
        editText_name = findViewById(R.id.name);
        editText_calories = findViewById(R.id.calories);
        editText_proteins = findViewById(R.id.proteins);
        editText_carbohydrates = findViewById(R.id.carbohydrates);
        editText_fats = findViewById(R.id.fats);
        listView = findViewById(R.id.list_view);
        helper = new Helper(this);
        DataBased.context = getApplicationContext();
        adapterSet(helper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Everything clickedFood = (Everything) parent.getItemAtPosition(position);
                helper.deleteFood(clickedFood);
                adapterSet(helper);
                showToast(DataBased.this, "Удалён объект " + clickedFood.toString());
            }
        });
    }

    @SuppressLint("ShowToast")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.getData: {
                adapterSet(helper);
                break;
            }
            case R.id.add: {
                food_name = editText_name.getText().toString();
                calories = Integer.parseInt(editText_calories.getText().toString());
                proteins = Integer.parseInt(editText_proteins.getText().toString());
                carbohydrates = Integer.parseInt(editText_carbohydrates.getText().toString());
                fats = Integer.parseInt(editText_fats.getText().toString());
                try {
                    stuff = new Everything(-1, food_name, calories, proteins, carbohydrates, fats);
                    showToast(this, "Успешно сохранено в БД");
                } catch (Exception ex){
                    showToast(this, "Не вышло сохранить в БД");
                }
                helper = new Helper(DataBased.this);
                boolean sucess = helper.setAmountToDB(stuff);
                showToast(this, "Успешный Результат = " + Boolean.toString(sucess));
                adapterSet(helper);
                break;
            }
            case R.id.del: {
                showToast(this, "Чтобы удалить, нажмите на элемент в списке!");
                break;
            }
            case R.id.back: {
                this.finish();
                break;
            }
        }
    }

    public static Context getAppContext() {
        return DataBased.context;
    }

    public static void showToast(Context mContext,String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void adapterSet(Helper helper){
        List<Everything> everything = helper.getEverything();

        foodArrayAdapter = new ArrayAdapter<Everything>(this, android.R.layout.simple_list_item_1, everything);
        listView.setAdapter(foodArrayAdapter);
    }
}