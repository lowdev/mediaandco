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

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.MediaType;
import eu.entropy.mediapedia.utils.AppContext;

public class MainActivity extends AppCompatActivity {

    public static final String COUNTRY_MEDIA = "mediaCountry";

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    private SharedPreferences preferences;
    private MediaType mediaType;

    private MediaFragment mediaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupAppContext();

        this.mediaType = MediaType.TV;
        this.preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        this.mediaFragment = MediaFragment.newInstance(new ArrayList<Company>());
        getSupportFragmentManager().beginTransaction().replace(
                R.id.flContent, this.mediaFragment).commit();

        loadCompany(MediaType.TV, null);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

        mDrawer.setDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setTitle("Television");

        setupSpinner();
    }

    private void loadCompany(MediaType mediaType, String query) {
        CompanyLoader.newInstance(this, this.mediaFragment).execute(
                getCountryMedia(), mediaType.name(), query);
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
                loadCompany(MediaType.NONE, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
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
                mediaType = MediaType.TV;
                break;
            case R.id.nav_paper_fragment:
                mediaType = MediaType.PAPER;
                break;
            case R.id.nav_radio_fragment:
                mediaType = MediaType.RADIO;
                break;
            default:
                mediaType = MediaType.NONE;
        }

        loadCompany(mediaType, null);

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        mDrawer.closeDrawers();
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

                loadCompany(mediaType, null);
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
