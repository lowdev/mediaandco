package eu.entropy.mediapedia.company;


import android.content.res.AssetManager;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StakeholderRepository {

    private CompanyRepository companyRepository;

    public StakeholderRepository(AssetManager assetManager, Resources resources, String packageName) {
        companyRepository = new CompanyRepository(assetManager, resources, packageName);
    }

    public Stakeholder findById(String id, double stake) {
        return new Stakeholder(companyRepository.findById(id), stake);
    }

    public List<Stakeholder> findByIds(Map<String, Double> ids) {
        List<Stakeholder> stakeholders = new ArrayList<>();
        for(String id : ids.keySet()) {
            stakeholders.add(findById(id, ids.get(id)));
        }

        return stakeholders;
    }
}
