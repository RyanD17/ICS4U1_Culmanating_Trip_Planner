// Import Unirest and JSON libraries
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

// Import JSON and date libraries
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Import ArrayList library
import java.util.ArrayList;

// Create a class that will handle the API requests and responses
public class SkyScannerAPI {

    // Create a callback interface for handling responses
    public interface ResponseCallback {
        void onSuccess(ArrayList<String> result);
        void onFailure(UnirestException e);
    }

    // Create a method that takes the parameters and returns flights
    public void getFlights(String country, String currency, String locale,
                           String originPlace, String destinationPlace,
                           String outboundDate, String inboundDate,
                           int adults, int children, int infants,
                           ResponseCallback callback) {
        // Create a session with the parameters
        try {
            HttpResponse<JsonNode> sessionResponse = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
                    .header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "YOUR_RAPIDAPI_KEY")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("inboundDate", inboundDate)
                    .field("cabinClass", "economy")
                    .field("children", children)
                    .field("infants", infants)
                    .field("country", country)
                    .field("currency", currency)
                    .field("locale", locale)
                    .field("originPlace", originPlace)
                    .field("destinationPlace", destinationPlace)
                    .field("outboundDate", outboundDate)
                    .field("adults", adults)
                    .asJson();

            // Check if the session was created successfully
            if (sessionResponse.getStatus() == 201) {
                // Get the session key from the location header
                String location = sessionResponse.getHeaders().getFirst("Location");
                String sessionKey = location.substring(location.lastIndexOf("/") + 1);

                // Poll the session results with the session key
                HttpResponse<JsonNode> resultsResponse = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/uk2/v1.0/" + sessionKey + "?pageIndex=0&pageSize=10")
                        .header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                        .header("X-RapidAPI-Key", "YOUR_RAPIDAPI_KEY")
                        .asJson();

                // Check if the results were retrieved successfully
                if (resultsResponse.getStatus() == 200) {
                    // Get the JSON object from the response body
                    JSONObject jsonObject = resultsResponse.getBody().getObject();

                    // Get the itineraries array from the JSON object
                    JSONArray itineraries = jsonObject.getJSONArray("Itineraries");

                    // Initialize variables to store the best flight information
                    JSONObject bestItinerary = null;
                    double bestPrice = Double.MAX_VALUE;
                    String destination = null;
                    String outboundTime = null;
                    String inboundTime = null;

                    // Loop through the itineraries array and find the one with the lowest price
                    for (int i = 0; i < itineraries.length(); i++) {
                        // Get the current itinerary object
                        JSONObject itinerary = itineraries.getJSONObject(i);

                        // Get the pricing options array from the itinerary object
                        JSONArray pricingOptions = itinerary.getJSONArray("PricingOptions");

                        // Loop through the pricing options array and find the one with the lowest price
                        for (int j = 0; j < pricingOptions.length(); j++) {
                            // Get the current pricing option object
                            JSONObject pricingOption = pricingOptions.getJSONObject(j);

                            // Get the price from the pricing option object
                            double price = pricingOption.getDouble("Price");

                            // Compare the price with the best price so far and update if lower
                            if (price < bestPrice) {
                                bestPrice = price;
                                bestItinerary = itinerary;
                            }
                        }
                    }

                    // Check if a best itinerary was found
                    if (bestItinerary != null) {
                        // Get the outbound and inbound leg ids from the best itinerary object
                        String outboundLegId = bestItinerary.getString("OutboundLegId");
                        String inboundLegId = bestItinerary.getString("InboundLegId");

                        // Get the legs array from the JSON object
                        JSONArray legs = jsonObject.getJSONArray("Legs");

                        // Loop through the legs array and find the ones that match the leg ids
                        for (int i = 0; i < legs.length(); i++) {
                            // Get the current leg object
                            JSONObject leg = legs.getJSONObject(i);

                            // Get the leg id from the leg object
                            String legId = leg.getString("Id");

                            // Check if the leg id matches the outbound leg id
                            if (legId.equals(outboundLegId)) {
                                // Get the destination station id from the leg object
                                int destinationStationId = leg.getInt("DestinationStation");

                                // Get the arrival time from the leg object
                                String arrivalTime = leg.getString("Arrival");

                                // Format the arrival time as a string using SimpleDateFormat class
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                Date date = sdf.parse(arrivalTime);
                                sdf.applyPattern("yyyy-MM-dd HH:mm");
                                outboundTime = sdf.format(date);

                                // Get the places array from the JSON object
                                JSONArray places = jsonObject.getJSONArray("Places");

                                // Loop through the places array and find the one that matches the destination station id
                                for (int j = 0; j < places.length(); j++) {
                                    // Get the current place object
                                    JSONObject place = places.getJSONObject(j);

                                    // Get the place id from the place object
                                    int placeId = place.getInt("Id");

                                    // Check if the place id matches the destination station id
                                    if (placeId == destinationStationId) {
                                        // Get the name from the place object
                                        destination = place.getString("Name");
                                        break;
                                    }
                                }
                            }

                            // Check if the leg id matches the inbound leg id
                            if (legId.equals(inboundLegId)) {
                                // Get the departure time from the leg object
                                String departureTime = leg.getString("Departure");

                                // Format the departure time as a string using SimpleDateFormat class
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                Date date = sdf.parse(departureTime);
                                sdf.applyPattern("yyyy-MM-dd HH:mm");
                                inboundTime = sdf.format(date);
                            }
                        }

                        // Create an ArrayList to store the best flight information
                        ArrayList<String> result = new ArrayList<String>();
                        result.add(destination);
                        result.add(outboundTime);
                        result.add(inboundTime);

                        // Call the callback with the result ArrayList
                        callback.onSuccess(result);
                    } else {
                        // Call the callback with a null ArrayList
                        callback.onSuccess(null);
                    }
                } else {
                    // Call the callback with an exception
                    callback.onFailure(new UnirestException("Failed to get results: " + resultsResponse.getStatusText()));
                }
            } else {
                // Call the callback with an exception
                callback.onFailure(new UnirestException("Failed to create session: " + sessionResponse.getStatusText()));
            }
        } catch (UnirestException e) {
            // Call the callback with an exception
            callback.onFailure(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}