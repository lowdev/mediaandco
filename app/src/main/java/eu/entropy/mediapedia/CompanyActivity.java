package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Company company = getIntent().getParcelableExtra("company");
        setupToolbar(company.getName());
        setupTablayout();

        StakeholderRepository stakeholderRepository = new StakeholderRepository(getAssets(),
                getResources(), getPackageName());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CompanyFragmentPagerAdapter(
                getSupportFragmentManager(),
                CompanyActivity.this,
                stakeholderRepository.findByIds(company.getOwners()),
                stakeholderRepository.findByIds(company.getAssets())
                )
        );

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
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
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(title);
        }
    }

    private void setupTablayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Owners"));
        tabLayout.addTab(tabLayout.newTab().setText("Assets"));
    }
}
