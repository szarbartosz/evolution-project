package JSONServices;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

        /*Rozmiar mapy (width, height)
        ilość energii początkowej zwierząt (startEnergy)
        ilość energii traconej w każdym dniu (moveEnergy)
        ilość energii zyskiwanej przy zjedzeniu rośliny (plantEnergy)
        proporcje dżungli do sawanny (jugnleRatio)
        */

public class WriteJSON {
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
        //parameters
        JSONObject details = new JSONObject();
        details.put("width", "100");
        details.put("height", "100");
        details.put("jungleRatio", "0.5");
        details.put("startEnergy", "40");
        details.put("moveEnergy", "1");
        details.put("plantEnergy", "2");


        try (FileWriter file = new FileWriter("simulationParameters.json")) {
            file.write(details.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
