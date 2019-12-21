package visualization;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJSON {
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
        //parameters
        JSONObject details = new JSONObject();
        details.put("width", "60");
        details.put("height", "60");
        details.put("jungleRatio", "0.5");
        details.put("startEnergy", "60");
        details.put("moveEnergy", "1");
        details.put("plantEnergy", "6");
        details.put("days", "200");
        details.put("animals", "400");
        details.put("refresh", "50");


        try (FileWriter file = new FileWriter("simulationParameters.json")) {
            file.write(details.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
