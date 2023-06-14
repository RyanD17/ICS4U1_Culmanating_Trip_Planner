package com.example.tripplanner;

//import statements
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

//this class inherits from the bookTrip class
public class cityAdder extends bookTrip {

    private TableRow tableMain;
    private TextView cloneButton;
    private Button stopButton; //a button to stop adding rows
    private boolean stop = false; //a boolean variable to control the loop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

       // tableMain = findViewById(R.id.tableMain);
        cloneButton = findViewById(R.id.addFlightBtn);
        stopButton = findViewById(R.id.stopBtn); //find the stop button by id

        //when the clone button is pressed, then this will run
        cloneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();

                //use a while loop to create rows until stop is true
                while (!stop) {

                    TableRow newRow = (TableRow) inflater.inflate(R.layout.activity_city_adder,null);

                    tableMain.addView(newRow);

                    //create a new table layout
                    TableLayout tableMain2 = new TableLayout(cityAdder.this);
                    tableMain2.setId(View.generateViewId());
                    tableMain2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    tableMain2.setStretchAllColumns(true);

                    //create a relative layout params object to set the layout below attribute
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.BELOW, tableMain.getId());

                    //set the params to the second table layout
                    tableMain2.setLayoutParams(params);

                    //create a new table row
                    TableRow newRow2 = new TableRow(cityAdder.this);
                    newRow2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                    //add views to the new table row
                    //for example, a text view
                    TextView textView = new TextView(cityAdder.this);
                    textView.setText("New Text View");
                    newRow2.addView(textView);

                    //add more views here

                    //add the new table row to the second table layout
                    tableMain2.addView(newRow2);

                    //add more rows here

                    //get the root relative layout and add the second table layout to it
                    RelativeLayout rootLayout = findViewById(R.id.rootLayout);
                    rootLayout.addView(tableMain2);
                }

            }
        });

        //when the stop button is pressed, then this will run
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set stop to true to end the loop
                stop = true;

            }
        });
    }
}
