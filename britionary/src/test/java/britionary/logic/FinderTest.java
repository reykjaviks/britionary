package britionary.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinderTest {

    Finder finder;

    public FinderTest() {
    }

    @Before
    public void setUp() {
        finder = new Finder();
    }
    
    @Test
    public void testFindJSONArrayResults() {
        String json = "{\n"
                + "    \"results\": []}\n";
        JSONObject response = new JSONObject(json);
        assertNotNull(finder.findJSONArray(response, "results"));
    }

}
