// Import Unirest and JSON libraries

// Import ArrayList library
// Import Unirest and JSON libraries
import android.widget.Toast;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

// Import ArrayList library
import java.util.ArrayList;
public class GrabFlights {
        // Create an instance of SkyscannerAPI class
        SkyScannerAPI skyscannerAPI = new SkyScannerAPI();

// Call the getFlights() method with the parameters you want and handle the result ArrayList in a callback or a listener
        skyscannerAPI.getFlights("US","CAD","en-US","SFO-sky","JFK-sky","2023-01-01","2023-09-01",2,0,0,new void ResponseCallback()) {
                public void onSuccess (ArrayList<String> result) {
                        // Handle success response
                        // Check if the result ArrayList is not null
                        if (result != null) {
                                // Get the destination, outboundTime, and inboundTime from the result ArrayList
                                String destination = result.get(0);
                                String outboundTime = result.get(1);
                                String inboundTime = result.get(2);

                                // Display the best flight information in a text view or a toast
                                // For example:
                                String message = "The best flight is to " + destination + ".\n" +
                                        "The outbound time is " + outboundTime + ".\n" +
                                        "The inbound time is " + inboundTime + ".";
                                Toast.makeText(GrabFlights.this, message, Toast.LENGTH_LONG).show();
                        } else {
                                // Display a message that no flights were found
                                Toast.makeText(GrabFlights.this, "No flights were found.", Toast.LENGTH_LONG).show();
                        }
                }

                public void onFailure (UnirestException e){
                        // Handle failure response
                        // For example, display an error message or a toast
                        // You can access the exception message using e.getMessage()
                        Toast.makeText(GrabFlights.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
        });
}