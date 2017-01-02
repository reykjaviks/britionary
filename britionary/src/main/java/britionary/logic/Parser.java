package britionary.logic;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    private Finder finder;
    private JSONObject response;
    private String str;

    // What can I use this for?
    public enum ID {
        RESULTS, LEXICAL_ENTRIES, ENTRIES, SENSES, SUBSENSES, SYNONYMS,
        REGIONS;
    }
    
    private String handleResults(JSONArray results) {
        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                return handleLexicalEntries(lexicalEntries);
            }
        }
        return "";
    }

    private String handleLexicalEntries(JSONArray lexicalEntries) {
        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                return handleEntries(entries);
            }
        }
        return "";
    }
    
    private String handleEntries(JSONArray entries) {
        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                return handleSenses(senses);
            }
        }
        return "";
    }
    
    private String handleSenses(JSONArray senses) {
        for (int i = 0; i < senses.length(); i++) {
            JSONArray synonyms = finder.findJSONArray(senses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                return handleSynonyms(synonyms);
            }
        }
        return "";
    }

    private String handleSynonyms(JSONArray synonyms) {
        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            if (synonym.has("text")) {
                return synonym.getString("text");
            }
        }
        return "";
    }



    public String parseJSON(String json) {

        finder = new Finder();
        response = new JSONObject(json);

        JSONArray results = finder.findJSONArray(response, "results");
        if (results == null) {
            return "Cannot find results.";
        }

        str = handleResults(results);
        return str;

    }
}
