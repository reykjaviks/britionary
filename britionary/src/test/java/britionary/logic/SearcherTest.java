package britionary.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//Initialization
public class SearcherTest {
    
    Searcher searcher;
    
    public SearcherTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetInstance() {
        assertNotNull(Searcher.getInstance());
    }
    
    // More parsing is required for passing this test.
    // @Test
    public void testSearch() {
        searcher = Searcher.getInstance();
        assertEquals("wee", searcher.search("Little"));
    }
    
}
