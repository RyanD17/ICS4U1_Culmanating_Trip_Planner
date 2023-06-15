package com.example.tripplanner;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class SkyscannerFlightSearch {

    private static final String API_KEY = "prtl6749387986743898559646983194";

    public static class FlightDetailsTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            // Create the URL for the Skyscanner API.
            URL url;
            try {
                url = new URL("https://partners.api.skyscanner.net/apiservices/pricing/v1.0/");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            // Set the request headers.
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("apiKey", API_KEY);
            conn.setRequestProperty("country", "CA");
            conn.setRequestProperty("currency", "CAD");
            conn.setRequestProperty("locale", "en-CA");

            // Set the request body.
            String body = "originplace=" + params[0] + "&destinationplace=" + params[1] + "&outbounddate=" + params[2] + "&inbounddate=" + params[3] + "&adults=" + params[4] + "&children=" + params[5] + "&youth=" + params[6];
            conn.setRequestProperty("Content-Length", Integer.toString(body.length()));

            // Send the request.
            PrintWriter writer;
            try {
                writer = new PrintWriter(conn.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.print(body);
            writer.close();

            // Get the response.
            int responseCode = 0;
            try {
                responseCode = conn.getResponseCode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (responseCode != 200) {
                try {
                    throw new IOException("Invalid response code: " + responseCode);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Scanner responseScanner;
            try {
                responseScanner = new Scanner(new InputStreamReader(conn.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Parse the response.
            List<String> flightDetails = new ArrayList<>();
            flightDetails.add(responseScanner.next());
            flightDetails.add(responseScanner.next());
            flightDetails.add(responseScanner.next());

            // Return the List.
            return flightDetails;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            // Do nothing.
        }
        public static List<String> getFlightDetails(String fromDate, String toDate, String fromLocation, String toLocation, String adults, String children, String infants) {

            FlightDetailsTask task = new FlightDetailsTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, fromDate, toDate, fromLocation, toLocation, adults, children, infants);
            try {
                return task.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//test comment
    }
}