package com.example.tripplanner;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class bookTrip extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);

        setDepartDate();
        setReturnDate();
        setAdults();
        setYouth();
        setChild();
        setInfant();
        appStopAction();

    }

    public void setFromText(){
        String fromLoc = "";



    }

    public void setDepartDate() {
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
                        String dateString = dateFormat.format(calendar.getTime());
                        departDate.setText(dateString);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Departure Date");
                mDatePicker.show();
            }
        });
    }

    public void setReturnDate() {
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
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String dateString = dateFormat.format(calendar.getTime());
                        returnDate.setText(dateString);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Return Date");
                mDatePicker.show();
            }
        });
    }

    void setAdults() {
        FloatingActionButton addAdults = findViewById(R.id.addAdult);
        FloatingActionButton removeAdults = findViewById(R.id.removeAdult);
        TextView numAdultsCounter = findViewById(R.id.numAdultCounter);
        final int[] numAdults = {0};

        addAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAdults[0]++;
                numAdultsCounter.setText(Integer.toString(numAdults[0]));
            }
        });

        removeAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAdults[0] -= 1;
                numAdultsCounter.setText(Integer.toString(numAdults[0]));
            }
        });
    }

    void setYouth(){
        FloatingActionButton addYouth = findViewById(R.id.addYouth);
        FloatingActionButton removeYouth = findViewById(R.id.removeYouth);
        TextView numYouth = findViewById(R.id.numYouthCounter);
        final int[] numYouthCounter = {0};

        addYouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numYouthCounter[0] += 1;
                numYouth.setText(Integer.toString(numYouthCounter[0]));
            }
        });
        removeYouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numYouthCounter[0] -= 1;
                numYouth.setText(Integer.toString(numYouthCounter[0]));
            }
        });
    }
    void setChild(){
        FloatingActionButton addChild = findViewById(R.id.addChild);
        FloatingActionButton removeChild =  findViewById(R.id.removeChild);
        TextView numChild = findViewById(R.id.numChildCounter);
        final int[] numChildCounter = {0};

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numChildCounter[0] += 1;
                numChild.setText(Integer.toString(numChildCounter[0]));
            }
        });

        removeChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numChildCounter[0] -= 1;
                numChild.setText(Integer.toString(numChildCounter[0]));
            }
        });
    }
    void setInfant(){
        FloatingActionButton addInfant = findViewById(R.id.addInfant);
        FloatingActionButton removeInfant = findViewById(R.id.removeInfant);
        TextView numInfant = findViewById(R.id.numInfantCounter);
        final int[] numInfantCounter = {0};

        addInfant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numInfantCounter[0] += 1;
                numInfant.setText(Integer.toString(numInfantCounter[0]));
            }
        });
        removeInfant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numInfantCounter[0] -= 1;
                numInfant.setText(Integer.toString(numInfantCounter[0]));
            }
        });
    }
    public void appStopAction(){
        FloatingActionButton addStop = findViewById(R.id.addStop);

        addStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCityAdder = new Intent(bookTrip.this, cityAdder.class);
                startActivity(openCityAdder);

            }
        });

    }

}