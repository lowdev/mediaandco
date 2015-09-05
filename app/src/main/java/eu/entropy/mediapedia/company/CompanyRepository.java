package eu.entropy.mediapedia.company;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
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

import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import eu.entropy.mediapedia.utils.AppContext;
import retrofit.Callback;
import retrofit.RestAdapter;

public class CompanyRepository {
    public static final String FOLDER = "company";
    public static final String SELECT = "select * where ";

    private AssetManager assetManager;
    private CompanyApigeeService service;

    public CompanyRepository() {
        this.assetManager = AppContext.getAssetManager();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();
        service = restAdapter.create(CompanyApigeeService.class);
    }

    public void findAll(CompanySpecification companySpecification, Callback<ApigeeCompanyResult> callback) {
        service.findAll(companySpecification.getClause(), callback);
    }

    public void findById(String companyId, Callback<ApigeeCompanyResult> callback) {
        service.findById(companyId, callback);
    }

    public void findByIds(Iterable<String> ids,  Callback<ApigeeCompanyResult> callback) {
        service.findAll(Joiner
                .on(" and ")
                .join(ids), callback);
    }

    public List<Company> findAll(CompanySpecification companySpecification) {
        return FluentIterable
                .from(findFileNamesByType(companySpecification.getMediaType(), companySpecification.getCountry()))
                .filter(new ByQuery(companySpecification.getQuery()))
                .transform(new ToCompany())
                .toList();
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

    private List<String> findFileNamesByType(MediaType type, String country) {
        List<String> mediaJsonFiles = new ArrayList<>();
        String folderName = type.getFolderName() + '/' + country;
        for (String mediaJsonFile : Arrays.asList(getAssetsByFolder(FOLDER + "/" + folderName))) {
            mediaJsonFiles.add(folderName + "/" + mediaJsonFile);
        }

        return mediaJsonFiles;
    }

    private class ByQuery implements Predicate<String> {
        private String query;

        public ByQuery(String query) {
            this.query = query;
        }

        @Override
        public boolean apply(String companyId) {
            if (companyId.isEmpty()) {
                return true;
            }

            return companyId.substring(companyId.lastIndexOf('/')).contains(query);
        }
    }

    private class ToCompany implements Function<String, Company> {
        @Override
        public Company apply(String companyId) {
            return findById(companyId);
        }
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
