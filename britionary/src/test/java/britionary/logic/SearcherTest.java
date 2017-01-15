package britionary.logic;

import static britionary.rig.Target.ALL;
import static britionary.rig.Target.BRITS;
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
        assertEquals("cookie\n"
                + "bicky\n", searcher.search("Biscuit", BRITS));
    }

    @Test
    public void testSearchBrits() {
        assertEquals("titchy\n"
                + "wee\n"
                + "dinky\n"
                + "ickle\n", searcher.search("Little", BRITS));
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
