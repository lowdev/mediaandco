package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class CompanyActivity extends AppCompatActivity {

    private Menu menu;
    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        company = getIntent().getParcelableExtra("company");
        setupToolbar(company.getName());
        openCompaniesListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //onBackPressed();

        switch (item.getItemId()) {
            case R.id.action_network_graph:
                openNetworkGraphFragment();
                return true;
            case R.id.action_view_list :
                openMenuCompaniesList();
                openCompaniesListFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openNetworkGraphFragment() {
        hideOption(R.id.action_network_graph);
        showOption(R.id.action_view_list);

        NetworkGraphFragment networkGraphFragment = NetworkGraphFragment.newInstance(company);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, networkGraphFragment);
        transaction.commit();

        findViewById(R.id.tabLayout).setVisibility(View.GONE);
    }

    private void openMenuCompaniesList() {
        hideOption(R.id.action_view_list);
        showOption(R.id.action_network_graph);
        findViewById(R.id.tabLayout).setVisibility(View.VISIBLE);
    }

    private void openCompaniesListFragment() {
        CompaniesListFragment companiesListFragment = new CompaniesListFragment();
        Bundle args = new Bundle();
        args.putParcelable("company", company);
        companiesListFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, companiesListFragment);
        transaction.commit();
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

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}
