package piu.britionary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

//Javadoc
public class Fetcher {

    private StringBuilder str;
    private String word;
    private final String appID;
    private final String addKey;
    
    public Fetcher() {
        this.appID = "19275027";
        this.addKey = "fbf42d1294623d8ecfe2f595a193fdaa";
    }
    public Fetcher(String appID, String appKey) {
        this.appID = appID;
        this.addKey = appKey;
    }
    //TODO: Should be private
    public String convertWord(String word) {
        
        str = new StringBuilder(word.toLowerCase());
        
        //Replace underscores
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '_') {
                str.setCharAt(i, ' ');
            }
        }
        //Remove digits
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }
        //Remove leading space
        if (Character.isSpaceChar(str.charAt(0))) {
            str.deleteCharAt(0);
        }
        //Remove tracing space
        if (Character.isSpaceChar(str.charAt(str.length() - 1))) {
            str.deleteCharAt(str.length() - 1);
        }
        this.word = str.toString();
        return this.word;
    }

    //Javadoc
    public String fetchJSON(String word) {

        //TODO: Raise exception
        if (this.appID == null || this.addKey == null
                || this.appID.isEmpty() || this.addKey.isEmpty()) {
            return "";
        }
        
        String wordID = convertWord(word);
        
        //Example code from https://developer.oxforddictionaries.com/documentation#/
        String language = "en";
        String link = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + wordID + "/synonyms;antonyms";

        try {
            URL url = new URL(link);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", this.appID);
            urlConnection.setRequestProperty("app_key", this.addKey);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    //Prototype
    public String convertJSON(String json) {
        JSONObject response = new JSONObject(json);
        JSONObject metadata = response.getJSONObject("metadata");
        return metadata.getString("provider");
        
        /*
         JSONObject response = new JSONObject(json);
         JSONArray results = response.getJSONArray("results");
         return results.getString(0);
        */
    }
}
