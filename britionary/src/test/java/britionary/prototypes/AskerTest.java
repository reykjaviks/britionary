package britionary.prototypes;

import java.io.ByteArrayInputStream;
import britionary.prototypes.AskerPrototype;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AskerTest {
    
    AskerPrototype asker;
    String data;
    
    public AskerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testScanException() {
        asker = new AskerPrototype(null);
        asker.scan();
        assertEquals(null, asker.getWord());
    }
    @Test
    public void testScan() {
        data = "Hello World.";
        asker = new AskerPrototype(new ByteArrayInputStream(data.getBytes()));
        asker.scan();
        assertEquals("Hello World.", asker.getWord());
    }

}
