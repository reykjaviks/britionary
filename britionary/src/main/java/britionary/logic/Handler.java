package britionary.logic;

import static britionary.logic.Target.BRITS;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-taulukoiden läpikäymiseen.
 */
public class Handler {

    private Target target;

    /**
     * Konstruktori luo uuden Handler-olion.
     *
     * @param   target  kohdesynonyymit: BRITS tai ALL
     */
    public Handler(Target target) {
        this.target = target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return this.target;
    }

    /**
     * Metodi hakee JSON-objektin juuren ja siellä sijaitsevat sanat käyttämällä
     * apuna luokan yksityisiä metodeita.
     *
     * @param   response        käsiteltävä JSON-objekti
     * @return                  HashSet löydetyistä sanoista
     * @throws  ParseException  jos JSON-objektin sisältä ei löydy results-taulukkoa
     */
    public HashSet<RegionalWord> handleResults(JSONObject response) throws ParseException {
        JSONArray results = Finder.findJSONArray(response, "results");
        if (results == null) {
            throw new ParseException("Cannot find JSON-array \"results.\"");
        }
        HashSet<RegionalWord> synonymSet = new HashSet<>();
        for (int i = 0; i < results.length(); i++) {
            JSONArray lexicalEntries = Finder.findJSONArray(results.getJSONObject(i), "lexicalEntries");
            if (lexicalEntries != null) {
                synonymSet.addAll(handleLexicalEntries(lexicalEntries));
            }
        }
        return synonymSet;
    }

    private HashSet<RegionalWord> handleLexicalEntries(JSONArray lexicalEntries) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < lexicalEntries.length(); i++) {
            JSONArray entries = Finder.findJSONArray(lexicalEntries.getJSONObject(i), "entries");
            if (entries != null) {
                synonymSet.addAll(handleEntries(entries));
            }
        }
        return synonymSet;
    }

    private HashSet<RegionalWord> handleEntries(JSONArray entries) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < entries.length(); i++) {
            JSONArray senses = Finder.findJSONArray(entries.getJSONObject(i), "senses");
            if (senses != null) {
                synonymSet.addAll(handleSenses(senses));
            }
        }
        return synonymSet;
    }

    private HashSet<RegionalWord> handleSenses(JSONArray senses) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < senses.length(); i++) {
            /*
            JSONArray synonyms = Finder.findJSONArray(senses.getJSONObject(i), "synonyms");
            if (synonyms != null) {
                synonymSet.addAll(handleRegionalSynonyms(synonyms));
            }
            */
            JSONArray subsenses = Finder.findJSONArray(senses.getJSONObject(i), "subsenses");
            if (subsenses != null) {
                synonymSet.addAll(handleSubsenses(subsenses));
            }
            JSONArray senseSynonyms = Finder.findJSONArray(senses.getJSONObject(i), "synonyms");
            if (senseSynonyms != null) {
                synonymSet.addAll(handleSynonyms(senseSynonyms));
            }
        }
        return synonymSet;
    }

    private HashSet<RegionalWord> handleSubsenses(JSONArray subsenses) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < subsenses.length(); i++) {
            JSONArray subsenseRegions = Finder.findJSONArray(subsenses.getJSONObject(i), "regions");
            JSONArray subsenseSynonyms = Finder.findJSONArray(subsenses.getJSONObject(i), "synonyms");
            if (subsenseSynonyms != null && subsenseRegions != null) {
                synonymSet.addAll(handleSubsenseSynonyms(subsenses, subsenseRegions, subsenseSynonyms));
            } else if (subsenseSynonyms != null) {
                synonymSet.addAll(handleSynonyms(subsenseSynonyms));
            }
        }
        return synonymSet;
    }

    private HashSet<RegionalWord> handleSubsenseSynonyms(JSONArray subsenses, JSONArray subsenseRegions, JSONArray subsenseSynonyms) {
        HashSet<RegionalWord> subsenseSynonymSet = new HashSet<>();
        boolean isBritish = false;
        for (int i = 0; i < subsenseRegions.length(); i++) {
            if (isBritish(subsenseRegions.getString(i))) {
                isBritish = true;
                break;
            }
        }
        if (isBritish) {
            for (int j = 0; j < subsenseSynonyms.length(); j++) {
                JSONObject subsenseSynonym = subsenseSynonyms.getJSONObject(j);
                if (subsenseSynonym.has("text")) {
                    RegionalWord regionalWord = new RegionalWord("British", subsenseSynonym.getString("text"));
                    subsenseSynonymSet.add(regionalWord);
                }
            }
        }
        return subsenseSynonymSet;
    }

    private boolean isBritish(String region) {
        return region.equals("British")
                || region.equals("Scottish")
                || region.equals("Irish")
                || region.equals("Northern English");
    }

    private HashSet<RegionalWord> handleSynonyms(JSONArray synonyms) {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        for (int i = 0; i < synonyms.length(); i++) {
            if (this.target.equals(BRITS)) {

                JSONObject synonym = synonyms.getJSONObject(i);
                JSONArray regions = Finder.findJSONArray(synonym, "regions");

                if (synonym.has("text") && regions != null) {
                    for (int j = 0; j < regions.length(); j++) {
                    RegionalWord regionalWord = new RegionalWord(regions.getString(j), synonym.getString("text"));
                    synonymSet.add(regionalWord);
                    }
                }

            } else {
                JSONObject synonym = synonyms.getJSONObject(i);
                if (synonym.has("text")) {
                    RegionalWord regionalWord = new RegionalWord("none", synonym.getString("text"));
                    synonymSet.add(regionalWord);
                }
            }
        }
        return synonymSet;
    }

}