package com.example.modeling_of_ap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import android.view.Gravity;
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

import com.example.modeling_of_ap.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.example.modeling_of_ap.AddData;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        topAppBar.setOnClickListener(v -> drawerLayout.openDrawer(Gravity.LEFT));
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            String item = menuItem.toString();
            if(item.equalsIgnoreCase("Продукты")) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                AddData catFragment = AddData.newInstance("Hey", "Васька");
                ft.replace(R.id.fragment_container_view, catFragment);
                ft.commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else if(item.equalsIgnoreCase("Потреблённые продукты")) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                AddTotal catFragment = AddTotal.newInstance("Hay", "Васька");
                ft.replace(R.id.fragment_container_view, catFragment);
                ft.commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
            return true;
        });
    }
}