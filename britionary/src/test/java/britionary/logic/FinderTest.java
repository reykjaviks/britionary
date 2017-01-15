package britionary.logic;

import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class FinderTest {

    @Test
    public void testFindJSONArrayResults() {
        String json = "{\n"
                + "    \"results\": []}\n";
        JSONObject response = new JSONObject(json);
        assertNotNull(Finder.findJSONArray(response, "results"));
    }

}