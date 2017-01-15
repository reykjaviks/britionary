package britionary.logic;

import hashsets.Creator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HandlerTest {

    private BufferedReader br;

    public JSONObject createJSONObject(String textfile) throws IOException {
        StringBuilder sb;
        String line;
        JSONObject response;
        try {
            br = new BufferedReader(new FileReader(textfile));
            line = br.readLine();
            sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            response = new JSONObject(sb.toString());

        } finally {
            br.close();

        }
        return response;
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
        JSONObject response = new JSONObject("{\n\"no results\": []}\n");
        Handler.handleResults(response);
    }

    @Test
    public void testBiscuit() throws IOException, ParseException {
        assertEquals(Creator.createBiscuitSet(), Handler.handleResults(
                createJSONObject("src\\test\\resources\\jsons\\biscuit.txt")));
    }

    @Test
    public void testLeisurely() throws IOException, ParseException {
        assertEquals(Creator.createLeisurelySet(), Handler.handleResults(
                createJSONObject("src\\test\\resources\\jsons\\leisurely.txt")));
    }

}