package britionary.logic;

import java.util.ArrayList;
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
    
    private ArrayList<String> handleResults(JSONArray results) {
        ArrayList<String> words = new ArrayList<>();
        
        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                words.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return words;
    }

    private ArrayList<String> handleLexicalEntries(JSONArray lexicalEntries) {
        ArrayList<String> words = new ArrayList<>();
        
        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                words.addAll(handleEntries(entries));
            }
        }
        return words;
    }
    
    private ArrayList<String> handleEntries(JSONArray entries) {
        ArrayList<String> words = new ArrayList<>();
        
        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                words.addAll(handleSenses(senses));
            }
        }
        return words;
    }

    private ArrayList<String> handleSenses(JSONArray senses) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < senses.length(); i++) {
            JSONArray synonyms = finder.findJSONArray(senses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
            JSONArray subsenses = finder.findJSONArray(senses.getJSONObject(i), "subsenses");
            if (subsenses != null) {
                words.addAll(handleSubsenses(subsenses));
            }
        }
        return words;
    }
    
    private ArrayList<String> handleSubsenses(JSONArray subsenses) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray synonyms = finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
        }
        return words;
    }

    private ArrayList<String> handleSynonyms(JSONArray synonyms) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            if (synonym.has("text")) {
                words.add(synonym.getString("text"));
            }
        }
        return words;
    }

    public String parseJSON(String json) {

        finder = new Finder();
        response = new JSONObject(json);

        JSONArray results = finder.findJSONArray(response, "results");
        if (results == null) {
            return "Cannot find results.";
        }

        str = handleResults(results).get(0);
        return str;
    }
}
