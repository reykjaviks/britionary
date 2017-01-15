package britionary.logic;

import britionary.common.Creator;
import britionary.common.HashSets;
import britionary.common.ParseException;
import britionary.common.RegionalWord;
import java.io.IOException;
import java.util.HashSet;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HandlerTest {

    Creator creator;

    @Before
    public void setup() {
        creator = new Creator();
        
    }

    @Test
    public void testFindResults() throws ParseException {
        String str = "";
        HashSet<RegionalWord> words = Handler.handleResults(
                new JSONObject("{\n\"results\": []}\n"));
        for (RegionalWord word : words) {
            str += word.getWord() + "\n";
        }
        assertEquals("", str);
    }

    @Test(expected = ParseException.class)
    public void testUnFindResults() throws Exception {
        Handler.handleResults(new JSONObject("{\n\"no results\": []}\n"));
    }

    @Test
    public void testBiscuit() throws IOException, ParseException {
        JSONObject response = new JSONObject(
                creator.createJSONObject("src\\test\\resources\\jsons\\biscuit.txt"));
        assertEquals(HashSets.newBiscuitSet(), Handler.handleResults(response));
    }

    @Test
    public void testLeisurely() throws IOException, ParseException {
        JSONObject response = new JSONObject(
                creator.createJSONObject("src\\test\\resources\\jsons\\leisurely.txt"));
        assertEquals(HashSets.newLeisurelySet(), Handler.handleResults(response));
    }

}