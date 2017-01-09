package britionary.logic;

import java.util.HashSet;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston parsimiseen.
 */
public class Parser {

    /**
     * Metodi parsii JSON-tiedoston.
     * 
     * @param   json            Parsittava JSON-tiedosto
     * @return                  Lista löydetyistä synonyymeista
     * @throws  ParseException  Heittää poikkeuksen jos merkkijono on tyhjä
     */
    public static String parseJSONBrits(String json) throws ParseException {
        JSONObject response = new JSONObject(json);
        HashSet<RegionalWord> wordSet = BritishHandler.handleResults(response);

        if (wordSet.isEmpty()) {
            throw new ParseException("No British synonyms forcat ");
        }
        String synonyms = "";
        for (RegionalWord word : wordSet) {
            synonyms += word.getWord() + "\n";
        }
        return synonyms;
    }

    public static String parseJSONAll(String json) throws ParseException {
        JSONObject response = new JSONObject(json);
        HashSet<RegionalWord> wordSet = Handler.handleResults(response);

        if (wordSet.isEmpty()) {
            throw new ParseException("No synonyms for ");
        }
        String synonyms = "";
        for (RegionalWord word : wordSet) {
            synonyms += word.getWord() + "\n";
        }
        return synonyms;
    }
}
