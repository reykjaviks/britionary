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
    
    @Test
    public void testSearch() {
        searcher = Searcher.getInstance();
        assertEquals("expert", searcher.search("Ace"));
    }
    
}
