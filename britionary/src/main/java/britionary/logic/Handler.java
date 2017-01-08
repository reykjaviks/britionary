package britionary.logic;

import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston sisältämien objektien ja taulukoiden
 * läpikäymiseen.
 */
public class Handler {

    /**
     * Metodi hakee JSON-tiedoston juuren ja siellä sijaitsevat sanat käyttämällä
     * apuna luokan yksityisiä metodeita.
     *
     * @param   response        JSON-tiedostosta tehty JSON-objekti
     * @return                  Lista löydetyistä sanoista
     * @throws  ParseException  jos response-objektista ei löydy taulukkoa "results"
     */
    public static HashSet<RegionalWord> handleResults(JSONObject response) throws ParseException {
        JSONArray results = Finder.findJSONArray(response, "results");
        if (results == null) {
            throw new ParseException("Cannot find JSON-array \"results.\"");
        }
        
        HashSet<RegionalWord> words = new HashSet<>();
        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                words.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return words;
    }

    private static HashSet<RegionalWord> handleLexicalEntries(JSONArray lexicalEntries) {
        HashSet<RegionalWord> words = new HashSet<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                words.addAll(handleEntries(entries));
            }
        }
        return words;
    }

    private static HashSet<RegionalWord> handleEntries(JSONArray entries) {
        HashSet<RegionalWord> words = new HashSet<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                words.addAll(handleSenses(senses));
            }
        }
        return words;
    }

    private static HashSet<RegionalWord> handleSenses(JSONArray senses) {
        HashSet<RegionalWord> words = new HashSet<>();

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

    private static HashSet<RegionalWord> handleSubsenses(JSONArray subsenses) {
        HashSet<RegionalWord> words = new HashSet<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray synonyms = Finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                words.addAll(handleSynonyms(synonyms));
            }
        }
        return words;
    }

    private static HashSet<RegionalWord> handleSynonyms(JSONArray synonyms) {
        HashSet<RegionalWord> words = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONArray regions = Finder.findJSONArray(synonyms.getJSONObject(i), "regions");
            if (regions != null) {
                words.addAll(handleRegions(regions, synonyms));
            }
        }
        return words;
    }
    
    // TODO: Rajaa alue Iso-Britanniaan
    private static HashSet<RegionalWord> handleRegions(JSONArray regions, JSONArray synonyms) {
        HashSet<RegionalWord> words = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            for (int j = 0; j < regions.length(); j++) {
                if (synonym.has("text")) {
                    RegionalWord wordResult = new RegionalWord(regions.getString(j),
                            synonym.getString("text"));
                    words.add(wordResult);
                }
            }
        }
        return words;
    }
}
