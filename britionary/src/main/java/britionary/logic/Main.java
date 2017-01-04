package britionary.logic;

public class Main {

    public static void main(String[] args) {
        
        String word = "Little";
        Searcher searcher = Searcher.getInstance();
        System.out.println(searcher.search(word));

    }
}
