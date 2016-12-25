package piu.dictionary;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FetcherTest {
    
    Fetcher fetcher;
    
    public FetcherTest() {
    }
    
    @Before
    public void setUp() {
        fetcher = new Fetcher();
    }

    @Test
    public void testFetchSynonym() {
        assertEquals("pulu", fetcher.fetchSynonym("little"));
    }
    
}
