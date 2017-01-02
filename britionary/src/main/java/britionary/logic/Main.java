package britionary.logic;

public class Main {

    public static void main(String[] args) {
        
        Searcher searcher = Searcher.getInstance();
        System.out.println(searcher.search("Ace"));
        
    }
}
