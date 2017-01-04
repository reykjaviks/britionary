package britionary.logic;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Luokka tarjoaa metodin JSON-tiedoston sisältämien taulukkojen 
 * etsimiseen.
 */
public class Finder {

    /**
     * Metodi hakee JSON-objektin sisältä halutun taulukon.
     * 
     * @param   object  Objekti, jossa taulukko sijaitsee
     * @param   arrayID Taulukon nimi
     * @return          Taulukko tai null, jos taulukkoa ei löydetty
     */
    public JSONArray findJSONArray(JSONObject object, String arrayID) {
        if (object.has(arrayID)) {
            return object.getJSONArray(arrayID);
        }
        return null;
    }
}
