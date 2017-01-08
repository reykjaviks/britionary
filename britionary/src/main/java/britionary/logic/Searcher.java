package britionary.logic;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Luokka tarjoaa metodeita sanojen hakemiseen ja erottaa kutsujan sovelluslogiikan 
 * alemmasta tasosta.
 */
public class Searcher {

    private final Fetcher fetcher;
    private static Searcher instance = null;

    private Searcher() {
        fetcher = new Fetcher();
    }

    /**
     * Metodi pitää huolen, että luokasta voi tehdä vain yhden ilmentymän 
     * kerrallaan.
     * 
     * @return          Ilmentymä
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
     * @param   word    Käyttäjän syöttämä hakusana
     * @return          Lista löydetyistä synonyymeista
     */
    public String search(String word) {
        String str;
        try {
            str = Converter.convert(word);
        } catch (StringIndexOutOfBoundsException e) {
            return "Index out of bounds: " + e;
        } catch (Exception e) {
            return "Cannot convert word: " + e;
        }

        try {
            str = fetcher.fetchJSON(str);
        } catch (MalformedURLException e) {
            return "Invalid URL: " + e;
        } catch (IOException e) {
            return "No results for \"" + str + "\"";
        } catch (Exception e) {
            return "Cannot fetch JSON-file: " + e;
        }

        try {
            return Parser.parseJSON(str);
        } catch (ParseException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Cannot parse JSON-file: " + e;
        }
    }
}
