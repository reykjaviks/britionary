package britionary.prototypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParserPrototype {

    public String parseJSONLoop(String json) {
        StringBuilder str = new StringBuilder();
        JSONObject response = new JSONObject(json);

        JSONArray results = response.getJSONArray("results");
        JSONObject result = results.getJSONObject(0);

        JSONArray lexicalEntries = result.getJSONArray("lexicalEntries");
        JSONObject lexicalEntry = lexicalEntries.getJSONObject(1); //175

        JSONArray entries = lexicalEntry.getJSONArray("entries");
        JSONObject entry = entries.getJSONObject(0);

        JSONArray senses = entry.getJSONArray("senses");
        JSONObject sense = senses.getJSONObject(0);

        JSONArray subsenses = sense.getJSONArray("subsenses");
        JSONObject subsense4 = subsenses.getJSONObject(3); //300

        JSONArray synonyms = subsense4.getJSONArray("synonyms");

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
            return "Cannot find synonyms for \"" + "this.newWord" + "\"";
        }
        return str.toString();
    }

    public String parseJSONManual(String json) {

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

        for (int i = 0; i < lexicalEntries.length(); i++) {
            lexicalEntries.getJSONObject(i);
        }

        return result.getString("id") + ": "
                + result.getString("language") + ": "
                + entries.length() + ": "
                + entry.getString("homographNumber") + ": "
                + subsense.getString("id") + ": " //genID_
                + senseSynonym.getString("id") + ": "
                + senseSynonymX.getString("id") + ": "
                + subSenseSynonym.getString("id") + ": "
                + subSenseSynonymX.getString("id");
    }
}
