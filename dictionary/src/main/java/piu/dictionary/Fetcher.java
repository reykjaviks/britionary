package piu.dictionary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Fetcher { 
    public String fetch(String word) {

        //Example code from https://developer.oxforddictionaries.com/documentation#/
        String language = "en";
        String word_id = word.toLowerCase();
        String link = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id + "/synonyms;antonyms";

        //TODO: replace with your own app id and app key
        final String app_id = "";
        final String app_key = "";
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
}
