package eu.entropy.mediapedia.networkgraph.visjs;

import android.content.res.AssetManager;
import android.content.res.Resources;

import eu.entropy.mediapedia.company.StakeholderRepository;

public class VisjsModelConverter {

    private StakeholderRepository stakeholderRepository;

    public VisjsModelConverter(AssetManager assetManager, Resources resources, String packageName) {
        stakeholderRepository = new StakeholderRepository(assetManager, resources, packageName);
    }

    public Node convert(){
        return null;
    }
}
