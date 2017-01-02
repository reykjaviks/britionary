package britionary.logic;

import britionary.prototypes.FinderPrototype;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    Finder finder;
    JSONObject response;

    public enum Id {

        RESULTS, LEXICALENTRIES, ENTRIES, SENSES, SUBSENSES, SYNONYMS,
        REGIONS;
    }

    public String parseJSON(String json) {

        finder = new Finder();
        response = new JSONObject(json);

        if (finder.findJSONArray(response, "results") == null) {
            return "Cannot find results.";
        }

        return "";

    }
}
