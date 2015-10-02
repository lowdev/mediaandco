package eu.entropy.mediapedia;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanySpecification;

public class CompanyLoader extends AsyncTask<String, Void, List<Company>> {
    private MediaLoader mediaLoader;

    public static CompanyLoader newInstance(Activity activity, MediaFragment mediaFragment) {
        return new CompanyLoader(activity, mediaFragment);
    }

    private CompanyLoader(Activity activity, MediaFragment mediaFragment) {
        this.mediaLoader = MediaLoader.newInstance(activity, mediaFragment);
    }

    @Override
    protected List<Company> doInBackground(String... params) {
        return mediaLoader.companyRepository.findAll(CompanySpecification.builder()
                .query(params[0])
                .build());
    }

    @Override
    protected void onPostExecute(final List<Company> result) {
        mediaLoader.onPostExecute(result);
    }
}
