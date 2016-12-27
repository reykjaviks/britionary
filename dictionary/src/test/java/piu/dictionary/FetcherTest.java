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
    public void testConvertLowerCase() {
        assertEquals("little", fetcher.convertWord("LITTLE"));
    }
    @Test
    public void testConvertUnderscore() {
        assertEquals("a great deal of", fetcher.convertWord("a_great_deal_of"));
    }
    @Test
    public void testConvertLastSpace() {
        assertEquals("little", fetcher.convertWord("little "));
    }
    @Test
    public void testConvertNumber() {
        assertEquals("little", fetcher.convertWord("littl3e4"));
    }
    
    
}
