package piu.dictionary;
public class WordSearcher {

    private Fetcher fetcher;
    private Reader reader;

    WordSearcher() {
        fetcher = new Fetcher();
        reader = new Reader();
    }

    public String search(String word) {
        return reader.convert(fetcher.fetch(word));
    }
}
