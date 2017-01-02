package britionary.logic;

public class Searcher {

    private Parser parser;
    private Fetcher fetcher;
    private Converter converter;
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
        converter.convert(word);
        return parser.parseJSON(fetcher.fetchJSON(converter.getWord()));
    }
}
