package britionary.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class Fetcher {

    private final String appID;
    private final String appKey;

    public Fetcher() {
        this.appID = "19275027";
        this.appKey = "fbf42d1294623d8ecfe2f595a193fdaa";
    }

    public Fetcher(String appID, String appKey) {
        this.appID = appID;
        this.appKey = appKey;
    }
    
    public String getAppID() {
        return this.appID;
    }

    public String getAppKey() {
        return this.appKey;
    }
    
    public boolean testCredentials() {
        if (this.appID == null || this.appKey == null
                || this.appID.isEmpty() || this.appKey.isEmpty()) {
            return false;
        }
        return true;
    }

    public String fetchJSON(String cleanWord) {

        if (testCredentials()) {

            // Example code from https://developer.oxforddictionaries.com/documentation#/
            String language = "en";
            String link = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + cleanWord + "/synonyms;antonyms";

            try {
                URL url = new URL(link);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", this.appID);
                urlConnection.setRequestProperty("app_key", this.appKey);

                //Read output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace(); // TODO: remove stack trace
                return e.toString();
            }
        }
        return "";
    }
}
