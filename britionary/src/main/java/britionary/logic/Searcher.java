package britionary.logic;

/**
 * Luokka tarjoaa metodin sanojen hakemiseen ja erottaa kutsujan 
 * sovelluslogiikan alemmista metodeista.
 */
public class Searcher {

    private final Parser parser;
    private final Fetcher fetcher;
    private final Converter converter;
    private static Searcher instance = null;

    private Searcher() {
        parser = new Parser();
        fetcher = new Fetcher();
        converter = new Converter();
    }

    /**
     * Metodi pitää huolen, että luokasta voi tehdä vain yhden ilmentymän.
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
        return parser.parseJSON(fetcher.fetchJSON(converter.convert(word)));
    }
}
