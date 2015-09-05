package eu.entropy.mediapedia;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.company.CompanySpecification;
import eu.entropy.mediapedia.company.MediaType;
import eu.entropy.mediapedia.utils.AppContext;

public class MainActivity extends AppCompatActivity {

    public static final String COUNTRY_MEDIA = "mediaCountry";

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    private SharedPreferences preferences;
    private CompanyRepository companyRepository;
    private MediaFragment mediaFragment;
    private List<Company> companies;
    private MediaType mediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupAppContext();

        this.preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        this.companyRepository = new CompanyRepository();
        this.companies = companyRepository.findAll(CompanySpecification.builder()
                .country(getCountryMedia())
                .mediaType(MediaType.TV)
                .build());
        this.mediaType = MediaType.TV;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void search(String query) {
        this.companies = companyRepository.findAll(CompanySpecification.builder()
                .country(getCountryMedia())
                .mediaType(MediaType.TV)
                .query(query)
                .build());
        updateCompanies();
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
                this.mediaType = MediaType.TV;
                this.companies = companyRepository.findAll(CompanySpecification.builder()
                        .mediaType(MediaType.TV)
                        .country(getCountryMedia())
                        .build());
                break;
            case R.id.nav_paper_fragment:
                this.mediaType = MediaType.PAPER;
                this.companies = companyRepository.findAll(CompanySpecification.builder()
                        .mediaType(MediaType.PAPER)
                        .country(getCountryMedia())
                        .build());
                break;
            case R.id.nav_radio_fragment:
                this.mediaType = MediaType.RADIO;
                this.companies = companyRepository.findAll(CompanySpecification.builder()
                        .mediaType(MediaType.RADIO)
                        .country(getCountryMedia())
                        .build());
                break;
            default:
                this.mediaType = MediaType.NONE;
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = ((TextView) view).getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(COUNTRY_MEDIA, text.toLowerCase());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setSelection(getIndex(spinner, getCountryMedia()));
    }

    private int getIndex(Spinner spinner, String countryMedia){
        for (int i = 0; i < spinner.getCount(); i++){
            String spinnerValue = (String) spinner.getItemAtPosition(i);
            if (spinnerValue.toLowerCase().equals(countryMedia)){
                return i;
            }
        }
        return 0;
    }

    @NonNull
    private String getCountryMedia() {
        return preferences.getString(COUNTRY_MEDIA, "france");
    }
}
