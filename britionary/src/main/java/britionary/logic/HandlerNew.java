package britionary.logic;

import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-merkkijonon sisältämien taulukoiden ja objektien
 * läpikäymiseen.
 */
public class HandlerNew {

    /**
     * Metodi hakee JSON-objektin juuren ja siellä sijaitsevat sanat käyttämällä
     * apuna luokan yksityisiä metodeita.
     *
     * @param   response        käsiteltävä JSON-objekti
     * @return                  HashSet löydetyistä sanoista
     * @throws  ParseException  jos JSON-objektin sisältä ei löydy results-taulukkoa
     */
    public static HashSet<RegionalWord> handleResults(JSONObject response) throws ParseException {
        JSONArray results = Finder.findJSONArray(response, "results");
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        if (results != null) {
            for (int i = 0; i < results.length(); i++) {
                JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
                if (lexicalEntries != null) {
                    synonymSet.addAll(handleLexicalEntries(lexicalEntries));
                }
            }
        } else { // ParseExceptionin pitäisi olla parserissa (?)
            throw new ParseException("Cannot find JSON-array \"results.\"");
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleLexicalEntries(JSONArray lexicalEntries) throws ParseException {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                synonymSet.addAll(handleEntries(entries));
            }
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleEntries(JSONArray entries) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                synonymSet.addAll(handleSenses(senses));
            }
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleSenses(JSONArray senses) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < senses.length(); i++) {
            JSONObject sense = senses.getJSONObject(i);
            JSONArray synonyms = Finder.findJSONArray(sense, "synonyms");
            JSONArray regions = Finder.findJSONArray(sense, "regions");
            JSONArray subsenses = Finder.findJSONArray(sense, "subsenses");

            if (subsenses != null) {
                synonymSet.addAll(handleSubsenses(subsenses));
            }
             /* hyppää handleSynonyms():iin, jos sense-olion sisältä löytyy
                synonyymi- ja/tai region-taulukoita. */
            if (synonyms != null && regions != null) {
                synonymSet.addAll(handleSynonyms(synonyms, regions));
                // break?
            } else if (synonyms != null) {
                synonymSet.addAll(handleSynonyms(synonyms));
            }
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleSubsenses(JSONArray subsenses) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONObject subsense = subsenses.getJSONObject(i);
            JSONArray synonyms = Finder.findJSONArray(subsense, "synonyms");
            JSONArray regions = Finder.findJSONArray(subsense, "regions");

            if (synonyms != null && regions != null ) {
                synonymSet.addAll(handleSynonyms(synonyms, regions));
                // break?
            } else if (synonyms != null) {
                synonymSet.addAll(handleSynonyms(synonyms));
            }
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleSynonyms(JSONArray synonyms) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            JSONArray regions = Finder.findJSONArray(synonym, "regions");

            // TODO: tiivistä koodia
            if (synonym.has("text") && regions != null) {
                for (int j = 0; j < regions.length(); j++) {
                RegionalWord regionalWord = new RegionalWord(regions.getString(j), synonym.getString("text"));
                synonymSet.add(regionalWord);
                }
            } else if (synonym.has("text")) {
                RegionalWord regionalWord = new RegionalWord("none", synonym.getString("text"));
                synonymSet.add(regionalWord);
            }
        }
        return synonymSet;
    }

    private static HashSet<RegionalWord> handleSynonyms(JSONArray synonyms, JSONArray regions) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        String str = "";
        for (int i = 0; i < regions.length(); i++) {
            str += regions.getString(i);
        }

        for (int i = 0; i < synonyms.length(); i++) {
            JSONObject synonym = synonyms.getJSONObject(i);
            if (synonym.has("text")) {
                RegionalWord regionalWord = new RegionalWord(str, synonym.getString("text"));
                synonymSet.add(regionalWord);
            }
        }
        return synonymSet;
    }

}
