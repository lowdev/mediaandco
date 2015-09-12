package eu.entropy.mediapedia.companyactivity;

import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class StakeholderLoader extends AsyncTask<Map<String, Double>, Void, List<Stakeholder>> {
    private StakeholderRepository stakeholderRepository;
    private CompaniesFragment companiesFragment;

    public static StakeholderLoader newInstance(CompaniesFragment companiesFragment) {
        return new StakeholderLoader(companiesFragment);
    }

    private StakeholderLoader(CompaniesFragment companiesFragment) {
        this.stakeholderRepository = new StakeholderRepository();
        this.companiesFragment = companiesFragment;
    }

    @Override
    protected List<Stakeholder> doInBackground(Map<String, Double>... params) {
        companiesFragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                companiesFragment.startWaiting();
            }
        });

        List<Stakeholder> stakeholders = stakeholderRepository.findByIds(params[0]);
        Collections.sort(stakeholders);

        return stakeholders;
    }

    @Override
    protected void onPostExecute(final List<Stakeholder> result) {
        companiesFragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                companiesFragment.updateView(result);
            }
        });
    }
}
