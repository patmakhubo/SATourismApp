package com.javaexam.satourismapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvPlace;
    private Spinner spProvinces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvPlace = findViewById(R.id.tvPlace);
        this.spProvinces = findViewById(R.id.spProvinces);
        spProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            SqliteHelper sqliteHelper = new SqliteHelper(MainActivity.this);
            PlaceDetails details;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                details = sqliteHelper.getPlaceDetails(spProvinces.getSelectedItem().toString());
                tvPlace.setText(details.getPlace());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                details = sqliteHelper.getPlaceDetails(spProvinces.getSelectedItem().toString());
                tvPlace.setText("");
            }
        });
    }
}