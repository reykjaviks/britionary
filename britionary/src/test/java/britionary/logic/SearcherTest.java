package britionary.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearcherTest {
    
    Searcher searcher;
    
    public SearcherTest() {
    }

    @Test
    public void testGetInstance() {
        assertNotNull(Searcher.getInstance());
    }
    
    @Test
    public void testSearchUnknownWord() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"adjfk\"", searcher.search("Adjfk"));
    }
    
    @Test
    public void testSearchUmlauted() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"äfäfäö\"", searcher.search("Äfäfäö"));
    }

    //@Test (doesn't work)
    public void testSearch() {
        searcher = Searcher.getInstance();
        assertEquals("feline\n"
                + "tabby\n"
                + "ginger tom\n"
                + "tortoiseshell\n"
                + "marmalade cat\n "
                + "mouser\n"
                + "wild cat\n"
                + "alley cat\n"
                + "pussy\n"
                + "pussy cat\n"
                + "puss\n"
                + "moggie\n"
                + "mog\n"
                + "grimalkin\n", searcher.search("Cat"));
    }
}
