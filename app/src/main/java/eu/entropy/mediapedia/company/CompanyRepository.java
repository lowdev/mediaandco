package eu.entropy.mediapedia.company;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.entropy.mediapedia.R;

public class CompanyRepository {

    public static final String MEDIA = "media";
    public static final String FOLDER = "company";

    private AssetManager assetManager;
    private Resources resources;
    private String packageName;

    public CompanyRepository(AssetManager assetManager, Resources resources, String packageName) {
        this.assetManager = assetManager;
        this.resources = resources;
        this.packageName = packageName;
    }

    public List<Company> findAllMedia() {
        List<String> mediaJsonFiles = new ArrayList<>();
        for (String mediaJsonFile : Arrays.asList(getAssetsByFolder(FOLDER))) {
            if (mediaJsonFile.startsWith(MEDIA)) {
                mediaJsonFiles.add(mediaJsonFile);
            }
        }

        return findByIds(mediaJsonFiles);
    }

    private Company findById(String companyId) {
        InputStream in = createInputStream(FOLDER + "/" + companyId);
        Company company = new GsonBuilder().create().fromJson(createBufferedReader(in), Company.class);

        int logoDrawableId = resources.getIdentifier(company.getLogo(), "drawable", packageName);
        if (logoDrawableId == 0) {
            logoDrawableId = R.drawable.android_logo;
        }
        company.setLogoDrawableId(logoDrawableId);

        return company;
    }

    public List<Company> findByIds(List<String> ids) {
        List<Company> companies = new ArrayList<>();
        for (String id : ids) {
            companies.add(findById(id));
        }

        return companies;
    }

    private String[] getAssetsByFolder(String folder) {
        try {
            return assetManager.list(folder);
        } catch (IOException e) {
            Log.e("mediapedia", folder + " folder not found", e);
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
        return new BufferedReader(createInputStreamReader(in), 8000);
    }

    private static InputStreamReader createInputStreamReader(InputStream in) {
        try {
            return new InputStreamReader(in, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.e("mediapedia","Wronrg encoding", e);
            return new InputStreamReader(in);
        }
    }
}
