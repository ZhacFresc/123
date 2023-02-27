package com.example.tablenews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.id_my_ads:
                Toast.makeText(this, "Pressed id_my_ads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_cars:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.smartphone_ads:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dm_ads:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_in:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_up:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_out:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}