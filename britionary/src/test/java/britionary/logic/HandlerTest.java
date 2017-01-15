package britionary.logic;

import britionary.common.Reader;
import britionary.common.Sets;
import britionary.common.ParseException;
import britionary.common.RegionalWord;
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
        assertEquals(Sets.newBiscuitSet(), Handler.handleResults(response));
    }

    @Test
    public void testLeisurely() throws IOException, ParseException {
        JSONObject response = new JSONObject(
                reader.read("src\\test\\resources\\jsons\\leisurely.txt"));
        assertEquals(Sets.newLeisurelySet(), Handler.handleResults(response));
    }

}
