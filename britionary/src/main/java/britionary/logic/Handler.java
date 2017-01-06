package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Handler {
    
    private final String[] objectList = { "lexicalEntries", "entries", 
        "senses", "synonyms", "subsenses" };
    
    // TODO: Korjaa rekursio
    public ArrayList<String> handleJSON(JSONArray array) {
        ArrayList<String> words = new ArrayList<>();
        
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
    
    public static ArrayList<String> handleResults(JSONArray results) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                words.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return words;
    }

    private static ArrayList<String> handleLexicalEntries(JSONArray lexicalEntries) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                words.addAll(handleEntries(entries));
            }
        }
        return words;
    }

    private static ArrayList<String> handleEntries(JSONArray entries) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                words.addAll(handleSenses(senses));
            }
        }
        return words;
    }

    private static ArrayList<String> handleSenses(JSONArray senses) {
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

    private static ArrayList<String> handleSubsenses(JSONArray subsenses) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray synonyms = Finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
        }
        return words;
    }

    // TODO: Poista duplikaatit
    private static ArrayList<String> handleSynonyms(JSONArray synonyms) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONArray regions = Finder.findJSONArray(synonyms.getJSONObject(i), "regions");
            if (regions != null) {
                words.addAll(handleRegions(regions, synonyms));
            }
        }
        return words;
    }
    
    // TODO: Poista duplikaatit, rajaa alue Iso-Britanniaan
    private static ArrayList<String> handleRegions(JSONArray regions, JSONArray synonyms) {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            for (int j = 0; j < regions.length(); j++) {
                words.add(regions.getString(j));
            }
            if (synonym.has("text")) {
                words.add(synonym.getString("text"));
            }
        }
        return words;
    }
}
