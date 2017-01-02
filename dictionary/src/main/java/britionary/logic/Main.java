package britionary.logic;

public class Main {

    public static void main(String[] args) {
        //TODO: research Singleton
        //Searcher searcher = new Searcher();
        Searcher searcher = Searcher.getInstance();
        System.out.println(searcher.search("Ace"));
    }
}
