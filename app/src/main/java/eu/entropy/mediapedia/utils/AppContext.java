package eu.entropy.mediapedia.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;

public class AppContext {
    public static AppContext build(AssetManager assetManager, Resources resources, String packageName) {
        return new AppContext(assetManager, resources, packageName);
    }

    private static AssetManager assetManager;
    private static Resources resources;
    private static String packageName;

    private AppContext(AssetManager assetManager, Resources resources, String packageName) {
        this.assetManager = assetManager;
        this.resources = resources;
        this.packageName = packageName;
    }

    public static AssetManager getAssetManager(){
        return assetManager;
    }

    public static Resources getResources(){
        return resources;
    }

    public static String getPackageName(){
        return packageName;
    }
}
