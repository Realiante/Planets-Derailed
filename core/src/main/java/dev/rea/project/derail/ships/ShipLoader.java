package dev.rea.project.derail.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;

import java.util.HashMap;
import java.util.Map;

public class ShipLoader {

    private static ShipLoader instance;
    private final Map<String, ShipModel> modelMap;


    private ShipLoader() {
        modelMap = new HashMap<>();
        FileHandle dirHandle = Gdx.files.internal("ships");
        JsonReader reader = new JsonReader();
        for (FileHandle entry : dirHandle.list()) {
            if (entry.extension().equals("json")) {
                ShipModel model = new ShipModel(reader.parse(entry));
                modelMap.put(model.shipID, model);
            }
        }
    }

    public static void init() {
        if (instance == null) {
            instance = new ShipLoader();
        }
    }

    public static ShipLoader getInstance() {
        init();
        return instance;
    }

    public ShipModel getModel(String id) {
        return modelMap.get(id);
    }

}
