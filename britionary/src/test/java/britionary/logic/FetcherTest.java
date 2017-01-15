package britionary.logic;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FetcherTest {

    Fetcher fetcher;

    public FetcherTest() {
    }

    @Before
    public void setUp() {
        fetcher = new Fetcher();
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

    @Test(expected = Exception.class)
    public void testFetchJSON() throws IOException {
        assertEquals("No results for \"hulking\"", fetcher.fetchJSON("hulking"));
    }
}