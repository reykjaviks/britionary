package britionary.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Luokka toteuttaa Oxford Dictionary:n määrittelemän ohjelmointirajapinnan.
 */
public class Fetcher {

    private final String appID;
    private final String appKey;

    /**
     * Konstruktori asettaa rajapintaan ennalta määritellyn käyttäjätunnuksen
     * ja salasanan.
     */
    public Fetcher() {
        this.appID = "19275027";
        this.appKey = "fbf42d1294623d8ecfe2f595a193fdaa";
    }

    /**
     * Konstruktori asettaa rajapintaan kutsujan määrittelemän käyttäjätunnuksen
     * ja salasanan.
     *
     * @param   appID   käyttäjätunnus
     * @param   appKey  salasana
     */
    public Fetcher(String appID, String appKey) {
        if (appID == null || appKey == null || appID.isEmpty() || appKey.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials.");
        } else {
            this.appID = appID;
            this.appKey = appKey;
        }
    }
    
    /**
     * Metodi hakee Oxford Dictionary:sta avainsanaa vastaavan JSON-merkkijonon.
     *
     * @param   keyword                 avainsana
     * @return                          avainsanaa vastaava JSON-merkkijono
     * @throws  MalformedURLException   jos linkki ei toimi
     * @throws  IOException             jos avainsanasta ei löydetty JSON-merkkijonoa
     */
    public String fetchJSON(String keyword) throws MalformedURLException, IOException {
        // Esimerkkikoodi: https://developer.oxforddictionaries.com/documentation#/
        String language = "en";
        String link = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + keyword + "/synonyms;antonyms";

        URL url = new URL(link);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("app_id", this.appID);
        urlConnection.setRequestProperty("app_key", this.appKey);

        // Read output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }

}