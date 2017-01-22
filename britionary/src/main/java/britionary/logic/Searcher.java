package britionary.logic;

import britionary.logic.common.ParseException;
import britionary.logic.common.Target;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Luokka tarjoaa metodeita sanojen hakemiseen, sekä erottaa kutsujan
 * sovelluslogiikan alemmasta tasosta.
 */
public class Searcher {

    private final Fetcher fetcher;
    private static Searcher instance = null;

    private Searcher() {
        fetcher = new Fetcher();
    }

    /**
     * Metodi varmistaa, että luokasta voi tehdä vain yhden ilmentymän
     * kerrallaan.
     * 
     * @return          ilmentymä
     */
    public static Searcher getInstance() {
        if (instance == null) {
            instance = new Searcher();
        }
        return instance;
    }
    
    /**
     * Metodi hakee syötettyä sanaa vastaavat synonyymit.
     * 
     * @param   word    hakusana
     * @param   target  BRITS tai ALL
     * @return          synonyymit
     */
    public String search(String word, Target target) {
        String keyword = Converter.convert(word);
        String json;
        try {
            json = fetcher.fetchJSON(keyword);
        } catch (MalformedURLException e) {
            return "Invalid URL: " + e;
        } catch (IOException e) {
            return "No results for \"" + keyword + "\"";
        } catch (Exception e) {
            return "Cannot fetch JSON-file: " + e;
        }

        try {
            return Parser.parseJSON(json, target);
        } catch (ParseException e) {
            return e.getMessage() + " for \"" + keyword + "\"";
        } catch (Exception e) {
            return "Cannot parse JSON-file: " + e;
        }
    }

}
