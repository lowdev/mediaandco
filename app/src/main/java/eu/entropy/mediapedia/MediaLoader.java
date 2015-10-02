package eu.entropy.mediapedia;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.company.CompanyRepositoryFactory;
import eu.entropy.mediapedia.company.CompanySpecification;
import eu.entropy.mediapedia.company.MediaType;

public class MediaLoader extends AsyncTask<String, Void, List<Company>> {
    protected CompanyRepository companyRepository;
    private Activity activity;
    private MediaFragment mediaFragment;

    public static MediaLoader newInstance(Activity activity, MediaFragment mediaFragment) {
        return new MediaLoader(activity, mediaFragment);
    }

    private MediaLoader(Activity activity, MediaFragment mediaFragment) {
        this.companyRepository = CompanyRepositoryFactory.get();
        this.mediaFragment = mediaFragment;
        this.activity = activity;
    }

    @Override
    protected List<Company> doInBackground(String... params) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mediaFragment.startWaiting();
            }
        });

        MediaType mediaType = MediaType.NONE;
        if (null != params[1]) {
            mediaType = MediaType.valueOf(params[1]);
        }

        return companyRepository.findAll(CompanySpecification.builder()
                .country(params[0])
                .mediaType(mediaType)
                .query(params[2])
                .build());
    }

    @Override
    protected void onPostExecute(final List<Company> result) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mediaFragment.updateView(result);
            }
        });
    }
}
