package britionary.logic;

//TODO: setup logic
public class Searcher {

    private Fetcher fetcher;

    public Searcher() { //TODO: change later
        fetcher = new Fetcher();
    }

    public String search(String word) {
        return fetcher.parseJSON(fetcher.fetchJSON(word));
    }

}
