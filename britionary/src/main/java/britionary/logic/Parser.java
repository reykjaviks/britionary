package britionary.logic;

import static britionary.logic.Target.ALL;
import static britionary.logic.Target.BRITS;
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
    public static String parseJSON(String json, Target target) throws ParseException {
        Handler handler;
        if (target.equals(BRITS))
            handler = new Handler(BRITS);
        else
            handler = new Handler(ALL);

        JSONObject response = new JSONObject(json);
        HashSet<RegionalWord> wordSet = handler.handleResults(response);
        if (wordSet.isEmpty())
            throw new ParseException("No regional synonyms for ");

        String synonyms = "";
        if (target.equals(BRITS)) {
            for (RegionalWord word : wordSet) {
                if (british(word))
                    synonyms += word.getWord() + "\n";
            }
            if (synonyms == "") {
                throw new ParseException("No British synonyms for ");
            }
            return synonyms;
        } else {
            for (RegionalWord word : wordSet)
                synonyms += word.getWord() + "\n";
            return synonyms;
        }
    }

    public static boolean british(RegionalWord word) {
        if (word.getRegion().equals("British")
                || word.getRegion().equals("Scottish")
                || word.getRegion().equals("Irish")) {
            return true;
        }
        return false;
    }
}
