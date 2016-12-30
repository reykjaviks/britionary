package britionary.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class Fetcher {

    private String newWord;
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

    //TODO: Should be private, replace loops with String.replace() 
    public String convertWord(String word) {

        StringBuilder str = new StringBuilder(word.toLowerCase());

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
        this.newWord = str.toString();
        return this.newWord;
    }

    //Prototype
    public JSONArray findResults(JSONObject response) {
        if (response.has("results")) {
            return response.getJSONArray("results");
        }
        return null;
    }
    
    public JSONObject findLexicalEntries(JSONArray results) {
        for (int i = 0; i < results.length(); i++) {
            if (results.getJSONObject(i).has("lexicalEntries")) {
                return results.getJSONObject(i);
            }
        }
        return null;
    }
    
    public JSONArray findEntries(JSONObject lexicalEntries) {
        if (lexicalEntries.has("entries")) {
            return lexicalEntries.getJSONArray("entries");
        }
        return null;
    }
       
    public String parseJSONNew(String json) {
        StringBuilder str = new StringBuilder();
        JSONObject response = new JSONObject(json);
        
        if (findResults(response) == null) {
            return "Cannot find results.";
        } else {
            if (findLexicalEntries((findResults(response))) == null) {
                return "Cannot find lexical entries.";
            } else {
                if (findEntries(findLexicalEntries((findResults(response)))) == null) {
                    return "Cannot find entries.";
                } else {
                    
                }
            }
        }

        return "Completion.";
    }

    //TODO: clean up
    public String parseJSON(String json) {
        StringBuilder str = new StringBuilder(); //TODO: change later
        JSONObject response = new JSONObject(json);
        
        JSONArray results = response.getJSONArray("results");
        JSONObject result = results.getJSONObject(0);

        JSONArray lexicalEntries = result.getJSONArray("lexicalEntries");
        //taulukossa on kolme entries-lohkoa, joista nyt haetaan järjestyksessä toinen.
        JSONObject lexicalEntry = lexicalEntries.getJSONObject(1); //175

        JSONArray entries = lexicalEntry.getJSONArray("entries");
        JSONObject entry = entries.getJSONObject(0);

        JSONArray senses = entry.getJSONArray("senses");
        JSONObject sense = senses.getJSONObject(0);

        JSONArray subsenses = sense.getJSONArray("subsenses");
        JSONObject subsense4 = subsenses.getJSONObject(3); //300

        JSONArray synonyms = subsense4.getJSONArray("synonyms");

        //TODO: clean up
        try {
            for (int i = 0; i < synonyms.length(); i++) {

                JSONObject synonym = synonyms.getJSONObject(i);
                JSONArray synonymRegions = synonym.getJSONArray("regions");

                for (int j = 0; j < synonymRegions.length(); j++) {
                    if (synonymRegions.getString(j).equals("British")
                            || synonymRegions.getString(i).equals("Scottish")
                            || synonymRegions.getString(i).equals("Irish")) {
                        str.append(synonymRegions.getString(i) + ": "
                                + synonym.getString("text") + "\n");
                    }
                }
            }
        } catch (Exception e) {
            return "Cannot find synonyms for \"" + this.newWord + "\"";
        }
        return str.toString();

        /*
         //TODO: make a method
         for (int i = 0; i < regions.length(); i++) {
         if (regions.getString(i).equals("British")
         || regions.getString(i).equals("Scottish")
         || regions.getString(i).equals("Irish")) {
         str.append(regions.getString(i) + "\n");
         }
         }
        
         for (int i = 0; i < lexicalEntries.length(); i++) {
         lexicalEntries.getJSONObject(i);
         }

         JSONObject response = new JSONObject(json);

        JSONObject metadata = response.getJSONObject("metadata");
        
        JSONArray results = response.getJSONArray("results");
        JSONObject result = results.getJSONObject(0); //contains id, lang and lexical entries
        
        JSONArray lexicalEntries = result.getJSONArray("lexicalEntries");
        JSONObject lexicalEntry = lexicalEntries.getJSONObject(0);
        
        JSONArray entries = lexicalEntry.getJSONArray("entries");
        JSONObject entry = entries.getJSONObject(0); //contains homographNumber and senses

        JSONArray senses = entry.getJSONArray("senses");
        JSONObject sense = senses.getJSONObject(0);

        JSONArray senseSynonyms = sense.getJSONArray("synonyms");
        JSONObject senseSynonym = senseSynonyms.getJSONObject(0);
        JSONObject senseSynonymX = senseSynonyms.getJSONObject(1);

        JSONArray subsenses = sense.getJSONArray("subsenses");
        JSONObject subsense = subsenses.getJSONObject(0);

        JSONArray subSenseSynonyms = subsense.getJSONArray("synonyms");
        
        JSONObject subSenseSynonym = subSenseSynonyms.getJSONObject(0);
        JSONObject subSenseSynonymX = subSenseSynonyms.getJSONObject(1);
  
        return  result.getString("id") + ": " 
                + result.getString("language") + ": " 
                + entries.length() + ": " 
                + entry.getString("homographNumber") + ": " 
                + subsense.getString("id") + ": " //genID_
                + senseSynonym.getString("id") + ": " 
                + senseSynonymX.getString("id") + ": " 
                + subSenseSynonym.getString("id") + ": "
                + subSenseSynonymX.getString("id");
        */
    }

    public String fetchJSON(String word) {

        //TODO: Raise exception
        if (this.appID == null || this.appKey == null
                || this.appID.isEmpty() || this.appKey.isEmpty()) {
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
            e.printStackTrace(); //TODO: remove stack trace
            return e.toString();
        }
    }
}
