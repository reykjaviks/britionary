package britionary.logic;

public class Main {

    public static void main(String[] args) {
        //TODO: research Singleton
        Searcher searcher = new Searcher();
        System.out.println(searcher.search("Ace"));
    }
}