package com.example.tripplanner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class cityAdder extends bookTrip {

    private TableLayout tableMain;
    private TextView cloneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

        tableMain = findViewById(R.id.tableMain);
        cloneButton = findViewById(R.id.addFlightBtn);


        cloneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = getLayoutInflater();

                @SuppressLint("ResourceType") TableRow clone = (TableRow) layoutInflater.inflate(R.id.rowToClone, tableMain, true);
                clone.setId(View.generateViewId());

            }
        });
    }
}