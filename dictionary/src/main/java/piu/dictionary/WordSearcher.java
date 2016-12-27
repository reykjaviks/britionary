package piu.dictionary;
public class WordSearcher {

    private Fetcher fetcher;

    WordSearcher() {
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return fetcher.convertJSON(fetcher.fetchJSON(word));
    }
}
