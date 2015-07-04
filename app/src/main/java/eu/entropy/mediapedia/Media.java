package eu.entropy.mediapedia;

import java.util.List;

/**
 * Created by Lowrey on 7/4/2015.
 */
public class Media {
    private int id;
    private String name;
    private String owner;
    private List<String> assets;

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
