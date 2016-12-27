package piu.dictionary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

//Javadoc
public class Fetcher {

    private StringBuilder str;
    private String word;
    
    //Javadoc
    public String convertWord(String word) {
        
        str = new StringBuilder(word.toLowerCase());
        
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == '_') {
                str.setCharAt(i, ' ');
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }

        if (Character.isSpaceChar(str.charAt(str.length() - 1))) {
            str.deleteCharAt(str.length() - 1);
        }
        
        this.word = str.toString();
        return this.word;
    }

    //Javadoc
    public String fetchJSON(String word) {

        String word_id = convertWord(word);
        
        //Example code from https://developer.oxforddictionaries.com/documentation#/
        String language = "en";
        String link = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id + "/synonyms;antonyms";

        //TODO: replace with your own app id and app key
        final String app_id = "19275027";
        final String app_key = "fbf42d1294623d8ecfe2f595a193fdaa";
        try {
            URL url = new URL(link);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

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
