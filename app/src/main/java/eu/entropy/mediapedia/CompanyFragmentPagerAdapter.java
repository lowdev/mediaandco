package eu.entropy.mediapedia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;

public class CompanyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static String TAB_TITLES[] = new String[] { "Owners", "Assets" };
    private List<Stakeholder> owners;
    private List<Stakeholder> assets;

    public CompanyFragmentPagerAdapter(FragmentManager fm, List<Stakeholder> owners, List<Stakeholder> assets) {
        super(fm);
        this.owners = owners;
        this.assets = assets;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return CompaniesFragment.newInstance(owners);
        }

        if (1 == position) {
            return CompaniesFragment.newInstance(assets);
        }

        throw new RuntimeException("Wrong position");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}