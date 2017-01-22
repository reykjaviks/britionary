package britionary.logic.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Luokkaa käytetään testien kirjoittamisen apuna.
 */
public class Reader {

    private BufferedReader br;

    /**
     * Metodi lukee tekstitiedoston.
     *
     * @param   textfile        luettava tekstitiedosto
     * @return                  tekstitiedoston sisältämät merkit
     * @throws  IOException     jos tekstitiedostoa ei löydy
     */
    public String read(String textfile) throws IOException {
        StringBuilder sb;
        String line;
        String str;
        try {
            br = new BufferedReader(new FileReader(textfile));
            line = br.readLine();
            sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            str = sb.toString();

        } finally {
            br.close();

        }
        return str;
    }

}
