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

    //TODO: use String.replace()
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

    public JSONObject findSenses(JSONArray entries) {
        for (int i = 0; i < entries.length(); i++) {
            if (entries.getJSONObject(i).has("senses")) {
                return entries.getJSONObject(i);
            }
        }
        return null;
    }
    
    public JSONArray findSubSenses(JSONObject senses) {
        if (senses.has("subsenses")) {
            return senses.getJSONArray("subsenses");
        }
        return null;
    }

    public JSONObject findSynonyms(JSONArray subsenses) {
        for (int i = 0; i < subsenses.length(); i++) {
            if (subsenses.getJSONObject(i).has("synonyms")) {
                return subsenses.getJSONObject(i);
            }
        }
        return null;
    }
    
    public JSONArray findRegions(JSONObject synonyms) {
        if (synonyms.has("regions")) {
            return synonyms.getJSONArray("regions");
        }
        return null;
    }

    public String findRegionalNames(JSONArray regions) {
        for (int i = 0; i < regions.length(); i++) {
            if (regions.getString(i).equals("British") || 
                    regions.getString(i).equals("Scottish") ||
                    regions.getString(i).equals("Irish")) {
                return regions.getString(i);
            }
        }
        return null;
    }
    
    /*
    Prototype 2.0
    TODO: remove long method calls 
    */
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
                    if (findSenses(findEntries(findLexicalEntries((findResults(response))))) == null) {
                        return "Cannot find senses.";
                    } else {
                        if (findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))) == null) {
                            return "Cannot find subsenses.";
                        } else {
                            if (findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response))))))) == null) {
                                return "Cannot find synonyms.";
                            } else {
                                if (findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))))) == null) {
                                    return "Cannot find regions.";
                                } else {
                                    if (findRegionalNames(findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response))))))))) == null) {
                                        return "Cannot find regional words.";
                                    } else {
                                        return "Region: " + findRegionalNames(findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
