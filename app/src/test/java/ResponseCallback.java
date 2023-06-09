import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

// Create a callback interface for handling responses
public interface ResponseCallback {
    void onSuccess(HttpResponse<JsonNode> response);

    void onFailure(UnirestException e);
}
