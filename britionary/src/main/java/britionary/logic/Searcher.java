package britionary.logic;

/**
 * Luokka tarjoaa metodin sanojen hakemiseen ja erottaa kutsujan 
 * sovelluslogiikan alemmista metodeista.
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
     * Metodi etsii synonyymeja kutsumalla Parser, Fetcher ja Converter 
     * -luokkien metodeita.
     * 
     * @param   word    Käyttäjän syöttämä hakusana
     * @return          Lista löydetyistä synonyymeista
     */
    public String search(String word) {
        return Parser.parseJSON(fetcher.fetchJSON(Converter.convert(word)));
    }
}
