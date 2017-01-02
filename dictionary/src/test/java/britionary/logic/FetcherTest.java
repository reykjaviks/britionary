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
    public void testConstructorID() {
        fetcher = new Fetcher("003", "fbf690");
        assertEquals("003", fetcher.getAppID());
    }
    @Test
    public void testConstructorKey() {
        fetcher = new Fetcher("003", "fbf690");
        assertEquals("fbf690", fetcher.getAppKey());
    }

    @Test
    public void testConstructorIDNull() {
        fetcher = new Fetcher(null, null);
        assertEquals(null, fetcher.getAppID());
    }
        @Test
    public void testConstructorKeyNull() {
        fetcher = new Fetcher(null, null);
        assertEquals(null, fetcher.getAppKey());
    }
}
