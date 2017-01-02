package britionary.logic;

//TODO: setup logic
public class Searcher {

    private Parser parser;
    private Fetcher fetcher;
    private Converter converter;

    public Searcher() {
        parser = new Parser();
        fetcher = new Fetcher();
        converter = new Converter();
    }

    public String search(String word) {
        converter.convert(word);
        return parser.parseJSON(fetcher.fetchJSON(converter.getWord()));
    }
}
