package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.utils.AppContext;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class MainActivity extends AppCompatActivity {

    private CompanyRepository companyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupAppContext();

        companyRepository = new CompanyRepository();
        setupRecyclerView();
    }

    private void setupAppContext() {
        AppContext.build(getAssets(), getResources(), getPackageName());
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        MediaLogoAdapter mediaLogoAdapter = new MediaLogoAdapter(companyRepository.findAllMedia());
        mediaLogoAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Company>() {
            public void onItemClick(View view, Company company) {
                Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
                intent.putExtra("company", company);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mediaLogoAdapter);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        // Show menu icon
        //final ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);
    }
}
