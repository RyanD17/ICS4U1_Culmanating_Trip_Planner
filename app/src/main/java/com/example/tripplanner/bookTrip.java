package com.example.tripplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class bookTrip extends MainActivity {
    // creating public instance variables
    public String fromLoc = "";
    public String toLoc = "";

    public String departDateStr;
    public String returnDateStr;

    public int numYouth = 0;
    public int numAdults = 0;
    public int child = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);

        // Set the departure date
        setDepartDate();

        // Set the return date
        setReturnDate();

        // Set the number of adults
        setAdults();

        // Set the number of youth
        setYouth();

        // Set the number of children
        setChild();

        // Get the "From" location text
        getFromText();

        // Get the "To" location text
        getToText();

        // Run the API
        runAPI();
    }

    // Get the "From" location text
    public String getFromText(){
        EditText fromInput = findViewById(R.id.fromLocText);
        fromLoc = fromInput.getText().toString();
        return fromLoc;
    }

    // Get the "To" location text
    public String getToText (){
        EditText toInput = findViewById(R.id.fromLocText);
        toLoc = toInput.getText().toString();
        return toLoc;
    }

    // Set the departure date
    public String setDepartDate() {
        TextView departDate = findViewById(R.id.departDateText);

        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(bookTrip.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        departDateStr = dateFormat.format(calendar.getTime());
                        departDate.setText(departDateStr);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Departure Date");
                mDatePicker.show();
            }
        });
        return departDate.toString();
    }

    // Set the return date
    public String setReturnDate() {
        TextView returnDate = findViewById(R.id.returnDateText);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(bookTrip.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/yyyy");
                        returnDateStr = dateFormat.format(calendar.getTime());
                        returnDate.setText(returnDateStr);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Return Date");
                mDatePicker.show();
            }
        });
        return returnDate.toString();
    }

    // Set the number of adults
    public int setAdults() {
        FloatingActionButton addAdults = findViewById(R.id.addAdult);
        FloatingActionButton removeAdults = findViewById(R.id.removeAdult);
        TextView numAdultsCounter = findViewById(R.id.numAdultCounter);

        addAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAdults ++;
                numAdultsCounter.setText(Integer.toString(numAdults));
            }
        });

        removeAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAdults -= 1;
                numAdultsCounter.setText(Integer.toString(numAdults));
            }
        });
        return numAdults;
    }

    // Set the number of youth
    public int setYouth(){
        FloatingActionButton addYouth = findViewById(R.id.addYouth);
        FloatingActionButton removeYouth = findViewById(R.id.removeYouth);
        TextView numYouthText = findViewById(R.id.numYouthCounter);

        addYouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numYouth += 1;
                numYouthText.setText(Integer.toString(numYouth));
            }
        });

        removeYouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numYouth -= 1;
                numYouthText.setText(Integer.toString(numYouth));
            }
        });
        return numYouth;
    }

    // Set the number of children
    public int setChild(){
        FloatingActionButton addChild = findViewById(R.id.addChild);
        FloatingActionButton removeChild =  findViewById(R.id.removeChild);
        TextView numChildText = findViewById(R.id.numChildCounter);

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child += 1;
                numChildText.setText(Integer.toString(child));
            }
        });

        removeChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child -= 1;
                numChildText.setText(Integer.toString(child));
            }
        });
        return child;
    }

    // Run the API
    public void runAPI() {
        Button runAPIbtn = findViewById(R.id.startAPI);

        runAPIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apiDisplay = new Intent(bookTrip.this, SkyscannerFlightSearch.class);
                startActivity(apiDisplay);
            }
        });
    }
}
