import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SkyscannerFlightSearch {

    //set the API key
    private static final String API_KEY = "prtl6749387986743898559646983194";

    public static void main(String[] args) throws IOException {
        // Ryan, need you to link these to the outputs from the page
        String locationFrom = ;
        String locationTo = ;
        String dateFrom = ;
        String dateTo = ;
        int adults = ;
        int youth = ;
        int children = ;

        // Create the URL for the Skyscanner API.
        URL url = new URL("https://partners.api.skyscanner.net/apiservices/pricing/v1.0/");

        // Set the request headers.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("apiKey", API_KEY);
        conn.setRequestProperty("country", "CA");
        conn.setRequestProperty("currency", "CAD");
        conn.setRequestProperty("locale", "en-CA");

        // Set the request body.
        String body = "originplace=" + locationFrom + "&destinationplace=" + locationTo + "&outbounddate=" + dateFrom + "&inbounddate=" + dateTo + "&adults=" + adults + "&children=" + children + "&youth=" + youth;
        conn.setRequestProperty("Content-Length", Integer.toString(body.length()));

        // Send the request.
        PrintWriter writer = new PrintWriter(conn.getOutputStream());
        writer.print(body);
        writer.close();

        // Get the response.
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Invalid response code: " + responseCode);
        }
        Scanner responseScanner = new Scanner(new InputStreamReader(conn.getInputStream()));

        // Parse the response.
        String outboundTime = responseScanner.next();
        String inboundTime = responseScanner.next();
        String bestPrice = responseScanner.next();
        //Ryan Grab these responses and output them
    }
}