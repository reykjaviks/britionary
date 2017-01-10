package britionary.logic;

import static britionary.logic.Target.ALL;
import java.util.HashSet;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HandlerTest {

    Handler handler;

    public HandlerTest() {
    }
    
    @Before
    public void setUp() {
        handler = new Handler(ALL);
    }

    @Test
    public void testHandleResults() throws Exception {
        String str = "";
        HashSet<RegionalWord> words = new HashSet<>();
        
        String json = "{\n"
                + "    \"results\": []}\n";
        JSONObject response = new JSONObject(json);
        words = handler.handleResults(response);
        
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
        handler.handleResults(response);
    }

}
