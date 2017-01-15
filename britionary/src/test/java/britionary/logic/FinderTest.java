package britionary.logic;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinderTest {

    @Test
    public void testFindJSONArrayResults() {
        String json = "{\n"
                + "    \"results\": []}\n";
        JSONObject response = new JSONObject(json);
        assertNotNull(Finder.findJSONArray(response, "results"));
    }

}
