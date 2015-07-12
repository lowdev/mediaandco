package eu.entropy.mediapedia.company;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    public static final String TELEVISION = "television";

    private AssetManager assetManager;
    private Resources resources;
    private String packageName;

    public CompanyRepository(AssetManager assetManager, Resources resources, String packageName) {
        this.assetManager = assetManager;
        this.resources = resources;
        this.packageName = packageName;
    }

    public List<Company> findAllMedia() {
        List<Company> companies = new ArrayList<>();
        for (String assetName : getAssetsName(TELEVISION)) {
            companies.add(findById(assetName));
        }

        return companies;
    }

    public Company findById(String assetName) {
        InputStream in = createInputStream(TELEVISION + "/" + assetName);
        Company company = new GsonBuilder().create().fromJson(createBufferedReader(in), Company.class);
        company.setDrawableIdLogo(resources.getIdentifier(company.getLogo(), "drawable", packageName));

        return company;
    }

    private String[] getAssetsName(String television) {
        try {
            return assetManager.list(television);
        } catch (IOException e) {
            Log.e("mediapedia", television + " folder not found", e);
            return new String[]{};
        }
    }

    private InputStream createInputStream(String mediaFileName) {
        try {
            return assetManager.open(mediaFileName);
        } catch (IOException e) {
            Log.e("file", mediaFileName + " file not found", e);
        }

        try {
            return assetManager.open("0.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedReader createBufferedReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
