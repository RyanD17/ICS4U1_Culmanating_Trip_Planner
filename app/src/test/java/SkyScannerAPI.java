// Import Unirest and JSON libraries
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

// Import SkyscannerAPI class
import com.example.skyscannerapi.SkyscannerAPI;


public class SkyScannerAPI {
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
                    .header("X-RapidAPI-Key", "prtl6749387986743898559646983194")
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
                        .header("X-RapidAPI-Key", "prtl6749387986743898559646983194")
                        .asJson();

                // Check if the results were retrieved successfully
                if (resultsResponse.getStatus() == 200) {
                    // Call the callback with the results response
                    callback.onSuccess(resultsResponse);
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
        }
    }
}


