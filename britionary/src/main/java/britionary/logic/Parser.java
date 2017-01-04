package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston parsimiseen.
 */
public class Parser {

    /**
     * Metodi parsii JSON-tiedoston käyttämällä apuna 
     * luokan yksityisiä metodeita.
     * 
     * @param   json    Parsittava JSON-tiedosto
     * @return          Lista löydetyistä synonyymeista
     */
    public String parseJSON(String json) {
        JSONObject response = new JSONObject(json);
        JSONArray results = Finder.findJSONArray(response, "results");
        if (results == null) {
            return "Cannot find results.";
        }

        String str = "";
        ArrayList<String> wordList = handleResults(results);
        for (int i = 0; i < wordList.size(); i++) {
            str += wordList.get(i) + "\n";
        }
        return str;
    }

    private ArrayList<String> handleResults(JSONArray results) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                words.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return words;
    }

    private ArrayList<String> handleLexicalEntries(JSONArray lexicalEntries) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                words.addAll(handleEntries(entries));
            }
        }
        return words;
    }

    private ArrayList<String> handleEntries(JSONArray entries) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                words.addAll(handleSenses(senses));
            }
        }
        return words;
    }

    private ArrayList<String> handleSenses(JSONArray senses) {
        ArrayList<String> words = new ArrayList<>();

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

    private ArrayList<String> handleSubsenses(JSONArray subsenses) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray synonyms = Finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
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
}
