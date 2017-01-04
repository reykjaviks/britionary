package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Finder {

    public JSONArray findJSONArray(JSONObject object, String arrayID) {
        if (object.has(arrayID)) {
            return object.getJSONArray(arrayID);
        }
        return null;
    }
}
