package com.example.modeling_of_ap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lin;
    private EditText editText;
    private Intent dataBase_activity;
    private Intent dayRatio_activity;
    Helper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
    String txt;
    public String[] data = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_new = findViewById(R.id.btn_new);
        btn_new.setOnClickListener(this);
        Button btn_new_act_add = findViewById(R.id.btn_new_act_add);
        btn_new_act_add.setOnClickListener(this);
        lin = findViewById(R.id.linear);
        editText = findViewById(R.id.new_el_from_data);
        userList = (ListView) findViewById(R.id.userList);
        dataBase_activity = new Intent(this, DataBased.class);
        dayRatio_activity = new Intent(this, DayRatio.class);
        sqlHelper = new Helper(this);
        db = sqlHelper.open();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Button button = new Button(MainActivity.this);
            button.setId(View.generateViewId());
            button.setHeight(130);
            button.setWidth(80);
            button.setText("Нету Bluetooth на телефоне");
            lin.addView(button);
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_new: {
                txt = editText.getText().toString();
                showToast(MainActivity.this, data[4]);
                // в ключ username пихаем текст из первого текстового поля
                dayRatio_activity.putExtra("foodData", data);
                // в ключ gift пихаем текст из второго текстового поля
                dayRatio_activity.putExtra("weight", Double.parseDouble(txt));
                startActivity(dayRatio_activity);
                break;
            }
            case R.id.btn_new_act_add: {
                startActivity(dataBase_activity);
                break;
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        sqlData();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void sqlData() {
        // открываем подключение
        db = sqlHelper.open();
        //получаем данные из бд в виде курсора
        userCursor = db.rawQuery("select * from " + Helper.FOOD_TYPES_TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{Helper.COLUMN_FOOD_NAME};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_multiple_choice,
                userCursor, headers, new int[]{android.R.id.text1}, 0);
        userList.setAdapter(userAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                for (int i = 1; i < 6; i++) {
                    data[i-1] = cursor.getString(i);
                }
            }
        });
    }
}