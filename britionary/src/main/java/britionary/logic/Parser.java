package britionary.logic;

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
     * @param   target          kohdesynonyymit: BRITS tai ALL
     * @return                  Lista löydetyistä synonyymeista
     * @throws  ParseException  Heittää poikkeuksen jos merkkijono on tyhjä
     */
    public static String parseJSON(String json, Target target) throws ParseException {
        //Handler handler = new Handler(target);
        JSONObject response = new JSONObject(json);
        HashSet<RegionalWord> wordSet = HandlerNew.handleResults(response);
        if (wordSet.isEmpty()) {
            throw new ParseException("No regional synonyms");
        }

        String synonyms = "";
        if (target.equals(BRITS)) {
            for (RegionalWord word : wordSet) {
                if (isBritish(word)) {
                    synonyms += word.getWord() + "\n";
                }
            }
            if (synonyms.equals("")) {
                throw new ParseException("No British synonyms");
            }
            return synonyms;
        } else {
            for (RegionalWord word : wordSet) {
                synonyms += word.getWord() + "\n";
            }
            return synonyms;
        }
    }

    /**
     * Metodi tarkistaa kuuluuko sana Iso-Britannian kielialueeseen.
     *
     * @param   word    tarkistettava sana
     * @return          true jos sana on brittiläinen
     */
    public static boolean isBritish(RegionalWord word) {
        return word.getRegion().contains("British")
                || word.getRegion().contains("Scottish")
                || word.getRegion().contains("Irish")
                || word.getRegion().contains("Northern English");
    }
}
