package britionary.logic;

//TODO: setup logic
public class Searcher {

    private Parser parser;
    private Fetcher fetcher;

    public Searcher() {
        parser = new Parser();
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return parser.parseJSON(fetcher.fetchJSON(word));
    }
}
