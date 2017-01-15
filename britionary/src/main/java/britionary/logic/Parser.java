package britionary.logic;

import britionary.common.ParseException;
import britionary.common.RegionalWord;
import britionary.common.Target;
import static britionary.common.Target.BRITS;
import java.util.HashSet;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston parsimiseen.
 */
public class Parser {

    /**
     * Metodi parsii JSON-merkkijonosta kohdesynonyymit.
     * 
     * @param   json            parsittava JSON-merkkijono
     * @param   target          BRITS tai ALL
     * @return                  merkkijono löydetyistä synonyymeista
     * @throws  ParseException  jos wordSet on tyhjä
     */
    public static String parseJSON(String json, Target target) throws ParseException {
        HashSet<RegionalWord> wordSet = Handler.handleResults(new JSONObject(json));
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
            return synonyms.trim();
        }
        for (RegionalWord word : wordSet) {
            synonyms += word.getWord() + "\n";
        }
        return synonyms.trim();
    }

    /**
     * Metodi tarkistaa kuuluuko sana Iso-Britannian kielialueeseen.
     *
     * @param   word    sana
     * @return          true jos sana on brittiläinen
     */
    public static boolean isBritish(RegionalWord word) {
        return word.getRegion().contains("British")
                || word.getRegion().contains("Scottish")
                || word.getRegion().contains("Irish")
                || word.getRegion().contains("Northern English");
    }
}
