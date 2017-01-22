package britionary.logic.prototypes;

import britionary.logic.Finder;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class HandlerPrototype {

    private final String[] objectList = {"lexicalEntries", "entries",
        "senses", "synonyms", "subsenses"};

    /**
     * Hakee JSON-tiedoston kaikki synonyymit maantieteellisestä alueesta
     * riippumatta.
     *
     * @param synonyms
     * @return Lista löydetyistä synonyymeista
     */
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

    // TODO: Korjaa rekursio
    public HashSet<String> handleJSON(JSONArray array) {
        HashSet<String> words = new HashSet<>();

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
}
