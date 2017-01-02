package britionary.logic;

import org.json.JSONArray;
import org.json.JSONObject;

public class Finder {

    public JSONArray findJSONArray(JSONObject object, String arrayID) {
        if (object.has(arrayID)) {
            return object.getJSONArray(arrayID);
        }
        return null;
    }

    public JSONObject findFirstJSONObject(JSONArray array, String objectID) {
        for (int i = 0; i < array.length(); i++) {
            if (array.getJSONObject(i).has(objectID)) {
                return array.getJSONObject(i);
            }
        }
        return null;
    }

}
