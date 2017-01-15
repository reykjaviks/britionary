package britionary.logic;

import static britionary.rig.Target.BRITS;

public class Main {

    /**
     * Pääohjelma hakee ja tulostaa hakusanaa vastaavat synonyymit.
     * 
     * @param   args    Ei käytössä
     */
    public static void main(String[] args) {
        
        String word = "Little";
        Searcher searcher = Searcher.getInstance();
        System.out.println(searcher.search(word, BRITS));

    }
}
