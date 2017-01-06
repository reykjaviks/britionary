package britionary.logic;

import java.util.HashSet;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandlerTest {
    
    public HandlerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testHandleResults() throws Exception {
        String str = "";
        HashSet<RegionalWord> words = new HashSet<>();
        
        String json = "{\n"
                + "    \"results\": []}\n";
        JSONObject response = new JSONObject(json);
        words = Handler.handleResults(response);
        
        for (RegionalWord word : words) {
            str += word.getWord() + "\n";
        }
        assertEquals("", str);
    }

    @Test(expected = ParseException.class)
    public void testHandleResults2() throws Exception {
        String json = "{\n"
                + "    \"nothing here\": []}\n";
        JSONObject response = new JSONObject(json);
        Handler.handleResults(response);
    }

}
