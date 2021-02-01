package com.example.modeling_of_ap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DayRatio extends AppCompatActivity implements View.OnClickListener {
    private String[] food_data;
    private String food_name;
    private double food_calories;
    private double food_proteins;
    private double food_carbohydrates;
    private double food_fats;
    private double weight;
    private Calculate stuff;
    private ListView foodList;
    private TextView text;
    private static Context context;
    private DayRatioHelper helper;
    private ArrayAdapter foodArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_ratio);
        Button get_Food_Data = findViewById(R.id.getFoodData);
        get_Food_Data.setOnClickListener(this);
        Button add_data = findViewById(R.id.btn_add);
        add_data.setOnClickListener(this);
        Button backToPrevious = findViewById(R.id.ret);
        backToPrevious.setOnClickListener(this);
        text = findViewById(R.id.foodData);
        foodList = findViewById(R.id.foodList);
        helper = new DayRatioHelper(this);
        food_data = getIntent().getExtras().getStringArray("foodData");
        weight = getIntent().getExtras().getDouble("weight");
        DayRatio.context = getApplicationContext();
        add();
        adapterSet(helper);
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calculate clickedFood = (Calculate) parent.getItemAtPosition(position);
                helper.deleteFood(clickedFood);
                adapterSet(helper);
                showToast(DayRatio.this, "Удалён объект " + clickedFood.toString());
            }
        });
    }


    @SuppressLint("ShowToast")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.getFoodData: {
                showToast(this, String.valueOf(helper.sumFoodColumn()));
                adapterSet(helper);
                break;
            }
            case R.id.btn_add: {
                add();
                break;
            }
            case R.id.ret: {
                this.finish();
                break;
            }
        }
    }

    public static Context getAppContext() {
        return DayRatio.context;
    }

    public static void showToast(Context mContext,String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void adapterSet(DayRatioHelper helper){
        List<Calculate> everything = helper.getEverything();

        foodArrayAdapter = new ArrayAdapter<Calculate>(this, android.R.layout.simple_list_item_1, everything);
        foodList.setAdapter(foodArrayAdapter);
    }
    public void add(){
        try {
            food_name = food_data[0];
            food_calories = Double.parseDouble(food_data[1]);
            food_proteins = Double.parseDouble(food_data[2]);
            food_carbohydrates = Double.parseDouble(food_data[3]);
            food_fats = Double.parseDouble(food_data[4]);
            try {
                stuff = new Calculate(-1, weight, food_name, food_calories, food_proteins, food_carbohydrates, food_fats);
                showToast(this, "Успешно сохранено в БД");
            } catch (Exception ex){
                showToast(this, "Не вышло сохранить в БД");
            }
            helper = new DayRatioHelper(DayRatio.this);
            boolean sucess = helper.setAmountToDB(stuff);
            showToast(this, "Успешный Результат = " + Boolean.toString(sucess));
            adapterSet(helper);

        } catch (NumberFormatException e) {
            showToast(this, "Надо писать через (.), а не через (,)");
        }
    }
}