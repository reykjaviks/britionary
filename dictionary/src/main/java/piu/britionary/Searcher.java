package piu.britionary;
public class Searcher {

    private Fetcher fetcher;

    Searcher() {
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return fetcher.convertJSON(fetcher.fetchJSON(word));
    }
}
