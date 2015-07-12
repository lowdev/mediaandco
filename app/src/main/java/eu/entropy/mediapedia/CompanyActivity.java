package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import org.solovyev.android.views.llm.DividerItemDecoration;
import org.solovyev.android.views.llm.LinearLayoutManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;


public class CompanyActivity extends AppCompatActivity {

    private CompanyRepository companyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        companyRepository = new CompanyRepository(getAssets(), getResources(), getPackageName());

        String companyFileName = getIntent().getStringExtra("mediaId") + ".json";
        Company company = companyRepository.findById(companyFileName);

        setupToolbar(company.getName());
        setupRecyclerView(company.getOwners(), R.id.owners);
        setupRecyclerView(company.getAssets(), R.id.assets);
    }

    private void setupRecyclerView(List<String> mediaAttributes, int recyclerViewId) {
        RecyclerView recyclerView = (RecyclerView) findViewById(recyclerViewId);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView, LinearLayoutManager.VERTICAL, false);
        layoutManager.setOverScrollMode(ViewCompat.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        CompanyAttributeAdapter companyAttributeAdapter = new CompanyAttributeAdapter(mediaAttributes);
        recyclerView.setAdapter(companyAttributeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(title);
    }
}
