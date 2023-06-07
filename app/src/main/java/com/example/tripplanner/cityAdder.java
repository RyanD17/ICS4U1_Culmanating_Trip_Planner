package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class cityAdder extends bookTrip {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);





    }
    public void setDepartDate() {
        TextView departDate = findViewById(R.id.departDate);

        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(cityAdder.this, new DatePickerDialog.OnDateSetListener() {
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
        TextView returnDate = findViewById(R.id.returnDate);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(cityAdder.this, new DatePickerDialog.OnDateSetListener() {
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


}