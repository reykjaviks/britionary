package britionary.prototypes;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class HandlerPrototype {

    private static ArrayList<String> handleSynonyms(JSONArray synonyms) {
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
