package britionary.logic;

import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class Handler {
    
    private final String[] objectList = { "lexicalEntries", "entries", 
        "senses", "synonyms", "subsenses" };
    
    // TODO: Korjaa rekursio
    public HashSet<String> handleJSON(JSONArray array) {
        HashSet<String> words = new HashSet<>();
        
        for (int i = 0; i < this.objectList.length; i++) {
            for (int j = 0; j < array.length(); j++) {
                JSONArray foundArray = Finder.findJSONArray(array.getJSONObject(j), objectList[i]);
                if (foundArray != null) {
                    words.addAll(handleJSON(foundArray));
                }
            }
        }
        return words;
    }
    
    public static HashSet<WordResult> handleResults(JSONObject response) throws ParseException {
        JSONArray results = Finder.findJSONArray(response, "results");
        if (results == null) {
            throw new ParseException("Cannot find results.");
        }
        
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                words.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return words;
    }

    private static HashSet<WordResult> handleLexicalEntries(JSONArray lexicalEntries) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                words.addAll(handleEntries(entries));
            }
        }
        return words;
    }

    private static HashSet<WordResult> handleEntries(JSONArray entries) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                words.addAll(handleSenses(senses));
            }
        }
        return words;
    }

    private static HashSet<WordResult> handleSenses(JSONArray senses) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < senses.length(); i++) {
            JSONArray synonyms = Finder.findJSONArray(senses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
            JSONArray subsenses = Finder.findJSONArray(senses.getJSONObject(i), "subsenses");
            if (subsenses != null) {
                words.addAll(handleSubsenses(subsenses));
            }
        }
        return words;
    }

    private static HashSet<WordResult> handleSubsenses(JSONArray subsenses) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray synonyms = Finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
        }
        return words;
    }

    private static HashSet<WordResult> handleSynonyms(JSONArray synonyms) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONArray regions = Finder.findJSONArray(synonyms.getJSONObject(i), "regions");
            if (regions != null) {
                words.addAll(handleRegions(regions, synonyms));
            }
        }
        return words;
    }
    
    // TODO: Rajaa alue Iso-Britanniaan
    private static HashSet<WordResult> handleRegions(JSONArray regions, JSONArray synonyms) {
        HashSet<WordResult> words = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            for (int j = 0; j < regions.length(); j++) {
                if (synonym.has("text")) {
                    WordResult wordResult = new WordResult(regions.getString(j),
                            synonym.getString("text"));
                    words.add(wordResult);
                }
            }
        }
        return words;
    }
}
