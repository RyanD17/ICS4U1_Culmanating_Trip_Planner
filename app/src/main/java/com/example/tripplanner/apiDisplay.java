package com.example.tripplanner;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class apiDisplay extends bookTrip {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_dsplay);

        // Declare a string array to store flight details
        String[] flightDetails;

        try {
            // Execute the FlightDetailsTask and retrieve the result
            flightDetails = new SkyscannerFlightSearch.FlightDetailsTask()
                    .execute(fromLoc, toLoc, departDateStr, returnDateStr,
                            Integer.toString(numAdults), Integer.toString(numYouth),
                            Integer.toString(child))
                    .get()
                    .toArray(new String[0]);

        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (InterruptedException e) {
            // Interrupt the current thread
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Set the flight details to the corresponding TextViews
        TextView outboundTimeTextView = findViewById(R.id.outboundTime);
        outboundTimeTextView.setText(flightDetails[0]);

        TextView inboundTimeTextView = findViewById(R.id.inboundTime);
        inboundTimeTextView.setText(flightDetails[1]);

        TextView bestPriceTextView = findViewById(R.id.bestPrice);
        bestPriceTextView.setText(flightDetails[2]);
    }
}
