package com.example.modeling_of_ap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lin;
    private EditText editText;
    private Intent dataBase_activity;

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
        dataBase_activity = new Intent(this, DataBased.class);
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
                String txt=editText.getText().toString();
                Button button = new Button(MainActivity.this);
                button.setId(View.generateViewId());
                button.setHeight(130);
                button.setWidth(80);
                button.setText(txt);
                lin.addView(button);
                break;
            }
            case R.id.btn_new_act_add: {
                startActivity(dataBase_activity);
                break;
            }
        }
    }
}