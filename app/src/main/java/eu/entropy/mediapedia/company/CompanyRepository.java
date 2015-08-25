package eu.entropy.mediapedia.company;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.entropy.mediapedia.R;
import eu.entropy.mediapedia.utils.AppContext;

public class CompanyRepository {

    public static final String TV = "tv";
    public static final String FRANCE = "france";
    public static final String PAPER = "paper";
    public static final String FOLDER = "company";

    private AssetManager assetManager;
    private Resources resources;
    private String packageName;

    public CompanyRepository() {
        this.assetManager = AppContext.getAssetManager();
        this.resources = AppContext.getResources();
        this.packageName = AppContext.getPackageName();
    }

    private List<Company> findAllByType(String type) {
        List<String> mediaJsonFiles = new ArrayList<>();
        for (String mediaJsonFile : Arrays.asList(getAssetsByFolder(FOLDER + "/" + type))) {
                mediaJsonFiles.add(type + "/" + mediaJsonFile);
        }

        return findByIds(mediaJsonFiles);
    }

    public List<Company> findAllTv(String country) {
        return findAllByType(TV + '/' + country);
    }

    public List<Company> findAllPaper(String country) {
        return findAllByType(PAPER + '/' + country);
    }

    public Company findById(String companyId) {
        InputStream in = createInputStream(FOLDER + "/" + companyId);

        Company company;
        try {
            company = new GsonBuilder().create().fromJson(createBufferedReader(in), Company.class);
        } catch (JsonSyntaxException e) {
            Log.e("mediapedia", "Can't read " + companyId, e);
            throw e;
        }

        return company;
    }

    public List<Company> findByIds(Iterable<String> ids) {
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
