import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        Model model = new Model(controller);
        controller.addView(view);
        controller.addModel(model);





    }
}
