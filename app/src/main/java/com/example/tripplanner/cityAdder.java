package com.example.tripplanner;


//import statements
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


//this class inherits from the bookTrip class
public class cityAdder extends bookTrip {

    private TableRow tableMain;
    private TextView cloneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

        tableMain = findViewById(R.id.tableMain);
        cloneButton = findViewById(R.id.addFlightBtn);


        //when the clone button is pressed, then this will run
        cloneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();

                TableRow newRow = (TableRow) inflater.inflate(R.layout.activity_city_adder,null);


                tableMain.addView(newRow);


            }
        });
    }
}