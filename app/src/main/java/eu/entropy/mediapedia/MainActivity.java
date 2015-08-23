package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.utils.AppContext;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    private CompanyRepository companyRepository;
    private MediaFragment mediaFragment;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupAppContext();

        this.companyRepository = new CompanyRepository();
        this.companies = companyRepository.findAllTv();
        this.mediaFragment = MediaFragment.newInstance(this.companies);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.flContent, this.mediaFragment).commit();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                updateCompanies();
            }
        };

        mDrawer.setDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setTitle("Television");

        setupSpinner();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_television_fragment:
                this.companies = companyRepository.findAllTv();
                break;
            case R.id.nav_paper_fragment:
                this.companies = companyRepository.findAllPaper();
                break;
            default:
                this.companies = new ArrayList<>();
        }

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        mDrawer.closeDrawers();
    }

    public void updateCompanies() {
        mediaFragment.updateView(companies);
    }

    private void setupAppContext() {
        AppContext.build(getAssets(), getResources(), getPackageName());
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
