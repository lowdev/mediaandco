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
import java.util.Arrays;
import java.util.List;

public class CompanyRepository {

    public static final String TELEVISION = "television";
    public static final String COMPANY = "company";

    private AssetManager assetManager;
    private Resources resources;
    private String packageName;

    public CompanyRepository(AssetManager assetManager, Resources resources, String packageName) {
        this.assetManager = assetManager;
        this.resources = resources;
        this.packageName = packageName;
    }

    public List<Company> findAllMedia() {
        return findByIds(Arrays.asList(getAssetsName(TELEVISION)), TELEVISION);
    }

    public List<Company> findCompanyByIds(List<String> ids) {
        return findByIds(ids, COMPANY);
    }

    private Company findById(String assetName, String type) {
        InputStream in = createInputStream(type + "/" + assetName);
        Company company = new GsonBuilder().create().fromJson(createBufferedReader(in), Company.class);
        company.setDrawableIdLogo(resources.getIdentifier(company.getLogo(), "drawable", packageName));

        return company;
    }

    private List<Company> findByIds(List<String> ids, String type) {
        List<Company> companies = new ArrayList<>();
        for (String id : ids) {
            companies.add(findById(id, type));
        }

        return companies;
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
