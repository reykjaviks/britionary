package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston parsimiseen.
 */
public class Parser {

    /**
     * Metodi parsii JSON-tiedoston käyttämällä apuna luokan yksityisiä metodeita.
     * 
     * @param   json    Parsittava JSON-tiedosto
     * @return          Lista löydetyistä synonyymeista
     */
    public static String parseJSON(String json) {
        JSONObject response = new JSONObject(json);
        ArrayList<String> wordList = Handler.handleResults(response);
        if (wordList == null) {
            return "Cannot find results.";
        }
        
        String str = "";
        for (int i = 0; i < wordList.size(); i++) {
            str += wordList.get(i) + "\n";
        }
        return str;
    }
}
