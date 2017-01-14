package britionary.logic;

import java.util.HashSet;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testHandleResultsBiscuit() throws Exception {
        String json = "{\n"
                + "  \"metadata\": {\n"
                + "    \"provider\": \"Oxford University Press\"\n"
                + "  },\n"
                + "  \"results\": [\n"
                + "    {\n"
                + "      \"id\": \"biscuit\",\n"
                + "      \"language\": \"en\",\n"
                + "      \"lexicalEntries\": [\n"
                + "        {\n"
                + "          \"entries\": [\n"
                + "            {\n"
                + "              \"senses\": [\n"
                + "                {\n"
                + "                  \"id\": \"t_en_gb0001444.001\",\n"
                + "                  \"regions\": [\n"
                + "                    \"British\"\n"
                + "                  ],\n"
                + "                  \"subsenses\": [\n"
                + "                    {\n"
                + "                      \"id\": \"genID_d82564e210054\",\n"
                + "                      \"regions\": [\n"
                + "                        \"North American\",\n"
                + "                        \"British\"\n"
                + "                      ],\n"
                + "                      \"synonyms\": [\n"
                + "                        {\n"
                + "                          \"id\": \"cookie\",\n"
                + "                          \"language\": \"en\",\n"
                + "                          \"text\": \"cookie\"\n"
                + "                        }\n"
                + "                      ]\n"
                + "                    },\n"
                + "                    {\n"
                + "                      \"id\": \"genID_d82564e210061\",\n"
                + "                      \"regions\": [\n"
                + "                        \"British\"\n"
                + "                      ],\n"
                + "                      \"registers\": [\n"
                + "                        \"informal\"\n"
                + "                      ],\n"
                + "                      \"synonyms\": [\n"
                + "                        {\n"
                + "                          \"id\": \"bicky\",\n"
                + "                          \"language\": \"en\",\n"
                + "                          \"text\": \"bicky\"\n"
                + "                        }\n"
                + "                      ]\n"
                + "                    }\n"
                + "                  ],\n"
                + "                  \"synonyms\": [\n"
                + "                    {\n"
                + "                      \"id\": \"cracker\",\n"
                + "                      \"language\": \"en\",\n"
                + "                      \"text\": \"cracker\"\n"
                + "                    },\n"
                + "                    {\n"
                + "                      \"id\": \"wafer\",\n"
                + "                      \"language\": \"en\",\n"
                + "                      \"text\": \"wafer\"\n"
                + "                    }\n"
                + "                  ]\n"
                + "                }\n"
                + "              ]\n"
                + "            }\n"
                + "          ],\n"
                + "          \"language\": \"en\",\n"
                + "          \"lexicalCategory\": \"Noun\",\n"
                + "          \"text\": \"biscuit\"\n"
                + "        }\n"
                + "      ],\n"
                + "      \"type\": \"headword\",\n"
                + "      \"word\": \"biscuit\"\n"
                + "    }\n"
                + "  ]\n"
                + "}";

        JSONObject response = new JSONObject(json);
        HashSet<RegionalWord> synonymSet = new HashSet<>();
        synonymSet.add(new RegionalWord("British", "cookie"));
        synonymSet.add(new RegionalWord("British", "bicky"));
        synonymSet.add(new RegionalWord("none", "cracker"));
        synonymSet.add(new RegionalWord("none", "wafer"));
        assertEquals(synonymSet, Handler.handleResults(response));

        /*
         for (RegionalWord word : handler.handleSynonyms(subsenses)) {
         System.out.println("Found " + word.getWord());
         }
         */
    }

}