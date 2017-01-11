package britionary.logic;

import static britionary.logic.Target.ALL;
import static britionary.logic.Target.BRITS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SearcherTest {
    
    Searcher searcher;
    
    public SearcherTest() {
    }

    @Before
    public void setUp() {
        searcher = Searcher.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(Searcher.getInstance());
    }
    
    @Test
    public void testSearchBritsUnknownWord() {
        assertEquals("No results for \"adjfk\"", searcher.search("Adjfk", BRITS));
    }
    
    @Test
    public void testSearchBritsUmlauted() {
        assertEquals("No results for \"äfäfäö\"", searcher.search("Äfäfäö", BRITS));
    }

    @Test
    public void testSearchAllUnknownWord() {
        assertEquals("No results for \"adjfk\"", searcher.search("Adjfk", ALL));
    }

    @Test
    public void testSearchAllUmlauted() {
        assertEquals("No results for \"äfäfäö\"", searcher.search("Äfäfäö", ALL));
    }

    @Test
    public void testSearchBritsNoSynonyms() {
        assertEquals("No regional synonyms for \"biscuit\"", searcher.search("Biscuit", BRITS));
    }

    @Test
    public void testSearchBrits() {
        assertEquals("teensy\n"
                + "teeny-weeny\n"
                + "teeny\n"
                + "itsy-bitsy\n"
                + "half-pint\n"
                + "wee\n"
                + "teensy-weensy\n"
                + "dinky\n"
                + "tiddly\n", searcher.search("Little", BRITS));
    }

    @Test
    public void testSearchAll() {
        assertEquals("measured\n"
                + "steady\n"
                + "laid-back\n", searcher.search("Leisurely", ALL));
    }
}
