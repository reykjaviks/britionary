package britionary.logic;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodeita JSON-objektin sisältämien taulukoiden etsimiseen.
 */
public class Finder {
    
    /**
     * Metodi hakee JSON-objektin sisältä halutun taulukon.
     * 
     * @param   object  objekti, jossa taulukko sijaitsee
     * @param   arrayID taulukon nimi
     * @return          taulukko tai null, jos taulukkoa ei löydetty
     */
    public static JSONArray findJSONArray(JSONObject object, String arrayID) {
        if (object.has(arrayID)) {
            return object.getJSONArray(arrayID);
        }
        return null;
    }

}