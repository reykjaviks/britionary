package britionary.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SearcherTest {
    
    Searcher searcher;
    
    public SearcherTest() {
    }

    @Test
    public void testGetInstance() {
        assertNotNull(Searcher.getInstance());
    }
    
    @Test
    public void testSearchBritsUnknownWord() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"adjfk\"", searcher.searchBrits("Adjfk"));
    }

    @Test
    public void testSearchBritsNoSynonyms() {
        searcher = Searcher.getInstance();
        assertEquals("No regional synonyms for \"reason\"", searcher.searchBrits("reason"));
    }
    
    @Test
    public void testSearchBritsUmlauted() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"äfäfäö\"", searcher.searchBrits("Äfäfäö"));
    }

    @Test
    public void testSearchAllUnknownWord() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"adjfk\"", searcher.searchAll("Adjfk"));
    }

    @Test
    public void testSearchAllUmlauted() {
        searcher = Searcher.getInstance();
        assertEquals("No results for \"äfäfäö\"", searcher.searchAll("Äfäfäö"));
    }

    @Test
    public void testSearchBrits() {
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

    @Test
    public void testSearchAll() {
        searcher = Searcher.getInstance();
        assertEquals("measured\n"
                + "steady\n"
                + "laid-back\n", searcher.searchAll("Leisurely"));
    }
}
