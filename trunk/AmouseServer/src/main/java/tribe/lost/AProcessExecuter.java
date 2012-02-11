package tribe.lost;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by Tobias Ericsson
 */
public class AProcessExecuter {

    private ProcessBuilder pB = null;

    public AProcessExecuter() {
        if (pB == null) {
            pB = new ProcessBuilder();
            pB.redirectErrorStream(true);
        }
    }

    public static String execute(String... command) {
        String response = "";
        try {
            ProcessBuilder pB = new ProcessBuilder();
            pB.redirectErrorStream(true);
            pB.command(command);
            Process p = pB.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = stdInput.readLine()) != null) {
                response = response + line + "\n";
            }
        } catch (IOException e) {
            return "IOEXception " + e.getMessage();
        }
        return response;
    }

}
