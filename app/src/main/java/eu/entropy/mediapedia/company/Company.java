package eu.entropy.mediapedia.company;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String id;
    private String name;
    private String logo;
    private int drawableIdLogo;
    private List<String> owners;
    private List<String> assets;

    public Company() {
        logo = "";
        owners = new ArrayList<>();
        assets = new ArrayList<>();
    }

    public String getId() {  return id; }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public int getDrawableIdLogo() {
        return drawableIdLogo;
    }

    public List<String> getOwners() {
        return owners;
    }

    public List<String> getAssets() {
        return assets;
    }

    public void setDrawableIdLogo(int drawableIdLogo) {
        this.drawableIdLogo = drawableIdLogo;
    }
}
