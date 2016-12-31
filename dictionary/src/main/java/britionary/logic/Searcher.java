package britionary.logic;

//TODO: setup logic
public class Searcher {
    private Fetcher fetcher;
    //TODO: research the necessity of this constructor.
    public Searcher() { 
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return fetcher.parseJSON(fetcher.fetchJSON(word));
    }
}
