package piu.dictionary;

import org.json.JSONObject;
import org.json.JSONArray;


public class Reader {
    
    public String convert(String json) {  
        JSONObject response = new JSONObject(json);
        JSONObject metadata = response.getJSONObject("metadata");
        return metadata.getString("provider");
    }
}
