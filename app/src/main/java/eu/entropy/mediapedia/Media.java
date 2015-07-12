package eu.entropy.mediapedia;

import java.util.ArrayList;
import java.util.List;

public class Media {
    private int id;
    private String name;
    private List<String> owners;
    private List<String> assets;

    public Media() {
        owners = new ArrayList<>();
        assets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getOwners() {
        return owners;
    }

    public List<String> getAssets() {
        return assets;
    }
}
