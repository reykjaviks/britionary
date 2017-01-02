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
    public void testConstructorEmptyID() {
        fetcher = new Fetcher("", "fbf690");
        assertEquals("", fetcher.getAppID());
    }

    @Test
    public void testConstructorEmptyKey() {
        fetcher = new Fetcher("003", "");
        assertEquals("", fetcher.getAppKey());
    }

    @Test
    public void testConstructorNullID() {
        fetcher = new Fetcher(null, "fbf690");
        assertEquals(null, fetcher.getAppID());
    }

    @Test
    public void testConstructorNullKey() {
        fetcher = new Fetcher("003", null);
        assertEquals(null, fetcher.getAppKey());
    }

    @Test
    public void testEmptyCredentialsTrue() {
        fetcher = new Fetcher("", "");
        assertTrue(fetcher.emptyCredentials());
    }

    @Test
    public void testEmptyCredentialsFalse() {
        fetcher = new Fetcher("003", "fbf690");
        assertFalse(fetcher.emptyCredentials());
    }

    @Test
    public void testEmptyCredentialsEmptyID() {
        fetcher = new Fetcher("", "fbf690");
        assertTrue(fetcher.emptyCredentials());
    }

    @Test
    public void testEmptyCredentialsEmptyKey() {
        fetcher = new Fetcher("003", "");
        assertTrue(fetcher.emptyCredentials());
    }

    @Test
    public void testEmptyCredentialsNullID() {
        fetcher = new Fetcher("003", null);
        assertTrue(fetcher.emptyCredentials());
    }

    @Test
    public void testEmptyCredentialsNullKey() {
        fetcher = new Fetcher(null, "fbf690");
        assertTrue(fetcher.emptyCredentials());
    }

}
