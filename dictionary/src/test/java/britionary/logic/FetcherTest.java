package britionary.logic;

import britionary.logic.Fetcher;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public void testConvertWordLowerCase() {
        assertEquals("little", fetcher.convertWord("LITTLE"));
    }
    
    @Test
    public void testConvertWordUnderscores() {
        assertEquals("a great deal of", fetcher.convertWord("a_great_deal_of"));
    }

    @Test
    public void testConvertWordNumbers() {
        assertEquals("little", fetcher.convertWord("littl3e4"));
    }

    @Test
    public void testConvertWordLeadingSpace() {
        assertEquals("wee", fetcher.convertWord(" wee"));
    }

    @Test
    public void testConvertWordTracingSpace() {
        assertEquals("wee", fetcher.convertWord("wee "));
    }

    @Test
    public void testConstructor() {
        fetcher = new Fetcher("", "");
        assertEquals("", fetcher.fetchJSON("little"));
    }

    @Test
    public void testConstructorNull() {
        fetcher = new Fetcher(null, null);
        assertEquals("", fetcher.fetchJSON("little"));
    }
}
