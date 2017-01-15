package britionary.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    private BufferedReader br;

    public String read(String textfile) throws IOException {
        StringBuilder sb;
        String line;
        String json;
        try {
            br = new BufferedReader(new FileReader(textfile));
            line = br.readLine();
            sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            json = sb.toString();

        } finally {
            br.close();

        }
        return json;
    }

}
