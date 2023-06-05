package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

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




}