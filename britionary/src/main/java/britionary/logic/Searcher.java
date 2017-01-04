package britionary.logic;

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

    public static Searcher getInstance() {
        if (instance == null) {
            instance = new Searcher();
        }
        return instance;
    }

    public String search(String word) {
        return parser.parseJSON(fetcher.fetchJSON(converter.convert(word)));
    }
}
