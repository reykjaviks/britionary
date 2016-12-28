package piu.britionary;

//TODO: setup logic
public class Searcher {

    private Fetcher fetcher;

    public Searcher() {
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return fetcher.parseJSON(fetcher.fetchJSON(word));
    }

}
