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
        assertEquals("No results for \"adjfk\"", searcher.searchBrits("Adjfk"));
    }
    
    @Test
    public void testSearchUmlauted() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"äfäfäö\"", searcher.searchBrits("Äfäfäö"));
    }

    @Test
    public void testSearch() {
        searcher = Searcher.getInstance();
        assertEquals("teensy\n"
                + "teeny-weeny\n"
                + "teeny\n"
                + "itsy-bitsy\n"
                + "half-pint\n"
                + "wee\n"
                + "teensy-weensy\n"
                + "dinky\n"
                + "tiddly\n", searcher.searchBrits("Little"));
    }
}
