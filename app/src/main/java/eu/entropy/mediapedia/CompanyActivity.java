package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import org.solovyev.android.views.llm.DividerItemDecoration;
import org.solovyev.android.views.llm.LinearLayoutManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;


public class CompanyActivity extends AppCompatActivity {

    private CompanyRepository companyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        Company company = (Company) getIntent().getParcelableExtra("company");
        setupToolbar(company.getName());

        companyRepository = new CompanyRepository(getAssets(), getResources(), getPackageName());
        setupRecyclerView(companyRepository.findCompanyByIds(company.getOwners()), R.id.owners);
        setupRecyclerView(companyRepository.findCompanyByIds(company.getAssets()), R.id.assets);
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

    private void setupRecyclerView(List<Company> companies, int recyclerViewId) {
        RecyclerView recyclerView = (RecyclerView) findViewById(recyclerViewId);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView, LinearLayoutManager.VERTICAL, false);
        layoutManager.setOverScrollMode(ViewCompat.OVER_SCROLL_IF_CONTENT_SCROLLS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        CompanyAttributeAdapter companyAttributeAdapter = new CompanyAttributeAdapter(companies);
        companyAttributeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Company>() {
            public void onItemClick(View view, Company company) {
                if (!company.hasInformation()) {
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
                intent.putExtra("company", company);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(companyAttributeAdapter);
    }
}
