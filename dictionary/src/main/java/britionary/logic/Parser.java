package britionary.logic;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    //TODO: implement further
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

    public JSONArray findSubSenses(JSONObject senses) {
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
    
    //TODO: remove long method calls
    public String parseJSON(String json) {
        StringBuilder str = new StringBuilder();
        JSONObject response = new JSONObject(json);

        if (findResults(response) == null) {
            return "Cannot find results.";
        } else {
            if (findLexicalEntries((findResults(response))) == null) {
                return "Cannot find lexical entries.";
            } else {
                if (findEntries(findLexicalEntries((findResults(response)))) == null) {
                    return "Cannot find entries.";
                } else {
                    if (findSenses(findEntries(findLexicalEntries((findResults(response))))) == null) {
                        return "Cannot find senses.";
                    } else {
                        if (findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))) == null) {
                            return "Cannot find subsenses.";
                        } else {
                            if (findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response))))))) == null) {
                                return "Cannot find synonyms.";
                            } else {
                                if (findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))))) == null) {
                                    return "Cannot find regions.";
                                } else {
                                    if (findRegionalNames(findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response))))))))) == null) {
                                        return "Cannot find regional words.";
                                    } else {
                                        return "Region: " + findRegionalNames(findRegions(findSynonyms(findSubSenses(findSenses(findEntries(findLexicalEntries((findResults(response)))))))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
