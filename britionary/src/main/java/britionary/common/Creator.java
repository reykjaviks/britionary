package britionary.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class Creator {

    private BufferedReader br;

    public JSONObject createJSONObject(String textfile) throws IOException {
        StringBuilder sb;
        String line;
        JSONObject response;
        try {
            br = new BufferedReader(new FileReader(textfile));
            line = br.readLine();
            sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            response = new JSONObject(sb.toString());

        } finally {
            br.close();

        }
        return response;
    }

}
