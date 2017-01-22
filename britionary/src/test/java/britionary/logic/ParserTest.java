package britionary.logic;

import britionary.logic.common.ParseException;
import britionary.logic.common.Reader;
import britionary.logic.common.RegionalWord;
import britionary.logic.common.Synonyms;
import static britionary.logic.common.Target.ALL;
import static britionary.logic.common.Target.BRITS;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

    Reader reader;

    @Before
    public void setup() {
        reader = new Reader();
    }

    @Test
    public void testIsBritishBritish() {
        RegionalWord word = new RegionalWord("British", "teeny-weeny");
        assertTrue(Parser.isBritish(word));
    }

    @Test
    public void testIsBritishScottish() {
        RegionalWord word = new RegionalWord("Scottish", "wee");
        assertTrue(Parser.isBritish(word));
    }

    @Test
    public void testIsBritishIrish() {
        RegionalWord word = new RegionalWord("Irish", "stook");
        assertTrue(Parser.isBritish(word));
    }

    @Test
    public void testIsBritishFinnish() {
        RegionalWord word = new RegionalWord("Finnish", "pikkuruinen");
        assertFalse(Parser.isBritish(word));
    }

    @Test
    public void testIsBritishNEnglish() {
        RegionalWord word = new RegionalWord("Northern English", "mumsy");
        assertTrue(Parser.isBritish(word));
    }
    
    @Test(expected = ParseException.class)
    public void testParseJSON() throws ParseException{
        Parser.parseJSON("{\n\"results\": []}\n", ALL);
    }

    @Test(expected = ParseException.class)
    public void testParseJSONEmpty() throws ParseException {
        Parser.parseJSON("{\n\"\": []}\n", ALL);
    }

    @Test
    public void testParseJSONBrits() throws IOException, ParseException {
        String json = reader.read("src\\test\\resources\\jsons\\little.txt");
        assertEquals(Synonyms.littleBrits(), Parser.parseJSON(json, BRITS));
    }

    @Test
    public void testParseJSONAll() throws IOException, ParseException {
        String json = reader.read("src\\test\\resources\\jsons\\little.txt");
        assertEquals(Synonyms.littleAll(), Parser.parseJSON(json, ALL));
    }
}
