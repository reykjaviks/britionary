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
        assertEquals("No British synonyms for \"biscuit\"", searcher.search("Biscuit", BRITS));
    }

    @Test
    public void testSearchBrits() {
        assertEquals("half-pint\n"
                + "teeny-weeny\n"
                + "itsy-bitsy\n"
                + "teensy\n"
                + "teeny\n"
                + "wee\n"
                + "teensy-weensy\n"
                + "dinky\n"
                + "tiddly\n", searcher.search("Little", BRITS));
    }

    @Test
    public void testSearchAll() {
        assertEquals("unhurried\n"
                + "unrushed\n"
                + "comfortable\n"
                + "slow\n"
                + "gentle\n"
                + "easy-going\n"
                + "sedate\n"
                + "steady\n"
                + "lackadaisical\n"
                + "measured\n"
                + "restful\n"
                + "effortless\n"
                + "languorous\n"
                + "languid\n"
                + "lazy\n"
                + "easy\n"
                + "laid-back\n"
                + "relaxed\n"
                + "undemanding\n"
                + "lingering\n", searcher.search("Leisurely", ALL));
    }
}
