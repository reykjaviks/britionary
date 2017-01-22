package britionary.logic;

import britionary.logic.common.Reader;
import britionary.logic.common.Sets;
import britionary.logic.common.ParseException;
import britionary.logic.common.RegionalWord;
import java.io.IOException;
import java.util.HashSet;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HandlerTest {

    Reader reader;

    @Before
    public void setup() {
        reader = new Reader();
        
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
                reader.read("src\\test\\resources\\jsons\\biscuit.txt"));
        assertEquals(Sets.createBiscuitSet(), Handler.handleResults(response));
    }

    @Test
    public void testLeisurely() throws IOException, ParseException {
        JSONObject response = new JSONObject(
                reader.read("src\\test\\resources\\jsons\\leisurely.txt"));
        assertEquals(Sets.createLeisurelySet(), Handler.handleResults(response));
    }

}
