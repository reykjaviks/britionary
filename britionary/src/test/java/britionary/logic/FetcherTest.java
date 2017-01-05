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

    @Test(expected = Exception.class)
    public void testConstructorEmptyID() {
        fetcher = new Fetcher("", "fbf690");
    }

    @Test(expected = Exception.class)
    public void testConstructorEmptyKey() {
        fetcher = new Fetcher("003", "");
    }

    @Test(expected = Exception.class)
    public void testConstructorNullID() {
        fetcher = new Fetcher(null, "fbf690");
    }

    @Test(expected = Exception.class)
    public void testConstructorNullKey() {
        fetcher = new Fetcher("003", null);
    }
}