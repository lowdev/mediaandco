package eu.entropy.mediapedia;

import java.util.ArrayList;
import java.util.List;

public class Media {
    private int id;
    private String name;
    private String owner;
    private List<String> assets;

    public Media() {
        assets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getAssets() {
        return assets;
    }
}
