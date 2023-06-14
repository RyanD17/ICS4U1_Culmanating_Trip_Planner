package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    protected Button newTrip; // creating a button object called newTrip
    protected BottomNavigationView navView; //Creating a Bottom Naviation View object called navView

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) { //When the activity has started, then the mainActivity screen is going to open
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.navBar); //setting the nav view variable to it's id in the mainactivity.xml file

        newTrip = findViewById(R.id.new_trip); // same thing as above but for new trip
        newTripIsPressed(); // calling the newTripIsPressed() method

    }


//calling the bookTrip class and using an intent to open the bookTrip page
    public void newTripIsPressed(){
        newTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bookTrip.class);
                startActivity(intent);
            }
        });
    }
}