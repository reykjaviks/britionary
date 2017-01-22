package britionary.logic.prototypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinderPrototype {

    //TODO: Korjaa metodi
    public static String findRegions(JSONObject synonym, JSONArray regions) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < regions.length(); i++) {
            if (regions.getString(i).equals("British")
                    || regions.getString(i).equals("Scottish")
                    || regions.getString(i).equals("Irish")) {
                str.append(regions.getString(i)
                        + ": " + synonym.getString("text") + "\n");
            }
        }
        return str.toString();
    }

    public JSONObject findFirstJSONObject(JSONArray array, String objectID) {
        for (int i = 0; i < array.length(); i++) {
            if (array.getJSONObject(i).has(objectID)) {
                return array.getJSONObject(i);
            }
        }
        return null;
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

    public JSONArray findSubenses(JSONObject senses) {
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
            if (regions.getString(i).equals("British")
                    || regions.getString(i).equals("Scottish")
                    || regions.getString(i).equals("Irish")) {
                return regions.getString(i);
            }
        }
        return null;
    }
}
