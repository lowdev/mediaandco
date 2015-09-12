package eu.entropy.mediapedia.companyactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.Stakeholder;

public class CompanyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TITLE_INFO = "Info";
    public static final String TITLE_OWNERS = "Owners";
    public static final String TITLE_ASSETS = "Assets";

    private List<String> tabTitles = new ArrayList<>(3);
    private Map<String, Fragment> titleToFragment = new HashMap<>();

    public CompanyFragmentPagerAdapter(FragmentManager fm, Company company) {
        super(fm);

        titleToFragment.put(TITLE_INFO, InformationFragment.newInstance(company));
        tabTitles.add(TITLE_INFO);

        Map<String, Double> owners = company.getOwners();
        if(!owners.isEmpty()) {
            titleToFragment.put(TITLE_OWNERS, CompaniesFragment.newInstance(owners));
            tabTitles.add(TITLE_OWNERS);
        }

        Map<String, Double> assets = company.getAssets();
        if(!assets.isEmpty()) {
            titleToFragment.put(TITLE_ASSETS, CompaniesFragment.newInstance(assets));
            tabTitles.add(TITLE_ASSETS);
        }
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Override
    public Fragment getItem(int position) {
        return titleToFragment.get(getPageTitle(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}