package eu.entropy.mediapedia.companyactivity;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class StakeholderLoader extends AsyncTask<Map<String, Double>, Void, List<Stakeholder>> {
    private StakeholderRepository stakeholderRepository;
    private StakeholdersFragment stakeholdersFragment;
    private Activity activity;

    public static StakeholderLoader newInstance(Activity activity, StakeholdersFragment stakeholdersFragment) {
        return new StakeholderLoader(activity, stakeholdersFragment);
    }

    private StakeholderLoader(Activity activity, StakeholdersFragment stakeholdersFragment) {
        this.stakeholderRepository = new StakeholderRepository();
        this.stakeholdersFragment = stakeholdersFragment;
        this.activity = activity;
    }

    @Override
    protected List<Stakeholder> doInBackground(Map<String, Double>... params) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stakeholdersFragment.startWaiting();
            }
        });

        List<Stakeholder> stakeholders = stakeholderRepository.findByIds(params[0]);
        Collections.sort(stakeholders);

        return stakeholders;
    }

    @Override
    protected void onPostExecute(final List<Stakeholder> result) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stakeholdersFragment.updateView(result);
            }
        });
    }
}
