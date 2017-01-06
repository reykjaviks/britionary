package britionary.logic;

import java.util.HashSet;
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
     * @throws britionary.logic.ParseException
     */
    public static String parseJSON(String json) throws ParseException {
        JSONObject response = new JSONObject(json);
        HashSet<WordResult> wordList = Handler.handleResults(response);
        
        String str = "";    
        for (WordResult word : wordList) {
            str += word.getWord() + "\n";
        }
        return str;
    }
}
