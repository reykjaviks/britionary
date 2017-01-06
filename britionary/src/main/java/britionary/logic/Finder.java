package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-tiedoston sisältämien taulukkojen etsimiseen.
 */
public class Finder {
    
    /**
     * Metodi hakee JSON-objektin sisältä halutun taulukon.
     * 
     * @param   object  Objekti, jossa taulukko sijaitsee
     * @param   arrayID Taulukon nimi
     * @return          Taulukko tai null, jos taulukkoa ei löydetty
     */
    public static JSONArray findJSONArray(JSONObject object, String arrayID) {
        if (object.has(arrayID)) {
            return object.getJSONArray(arrayID);
        }
        return null;
    }

    public static String findRegions(JSONObject synonym, JSONArray regions) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < regions.length(); i++) {
            if (regions.getString(i).equals("British")
                    || regions.getString(i).equals("Scottish")
                    || regions.getString(i).equals("Irish")) {
                str.append(regions.getString(i)
                        + ": " + synonym.getString("text") + "\n");
            }
        }
        return str.toString();
    }
}
