package britionary.logic;

import java.io.ByteArrayInputStream;
import britionary.logic.Asker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AskerTest {
    
    Asker asker;
    String data;
    
    public AskerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testScanException() {
        asker = new Asker(null);
        asker.scan();
        assertEquals(null, asker.getWord());
    }
    @Test
    public void testScan() {
        data = "Hello World.";
        asker = new Asker(new ByteArrayInputStream(data.getBytes()));
        asker.scan();
        assertEquals("Hello World.", asker.getWord());
    }

}
