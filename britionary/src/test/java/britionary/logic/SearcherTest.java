package britionary.logic;

import britionary.common.ParseException;
import britionary.common.Synonyms;
import static britionary.common.Target.ALL;
import static britionary.common.Target.BRITS;
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
    public void testSearchUnknownWordBrits() {
        assertEquals("No results for \"adjfk\"", searcher.search("Adjfk", BRITS));
    }
    
    @Test
    public void testSearchUmlautedBrits() {
        assertEquals("No results for \"äfäfäö\"", searcher.search("Äfäfäö", BRITS));
    }

    @Test
    public void testSearchUnknownWordAll() {
        assertEquals("No results for \"adjfk\"", searcher.search("Adjfk", ALL));
    }

    @Test
    public void testSearchAllUmlautedAll() {
        assertEquals("No results for \"äfäfäö\"", searcher.search("Äfäfäö", ALL));
    }

    @Test
    public void testSearchLittleBrits() {
        assertEquals(Synonyms.littleBrits(), searcher.search("Little", BRITS));
    }

    @Test
    public void testSearchLittleAll() {
        assertEquals(Synonyms.littleAll(), searcher.search("Little", ALL));
    }

    @Test
    public void testSearchLeisurelyAll() {
        assertEquals(Synonyms.leisurelyAll(), searcher.search("Leisurely", ALL));
    }

    @Test
    public void testSearchNoBritishWords() throws ParseException {
        assertEquals("No British synonyms for \"leisurely\"", searcher.search("Leisurely", BRITS));
    }

}
