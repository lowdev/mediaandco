package eu.entropy.mediapedia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;

public class CompanyFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Owners", "Assets" };
    private Context context;
    private List<Stakeholder> owners;
    private List<Stakeholder> assets;

    public CompanyFragmentPagerAdapter(FragmentManager fm, Context context,
                                       List<Stakeholder> owners, List<Stakeholder> assets) {
        super(fm);
        this.context = context;
        this.owners = owners;
        this.assets = assets;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return CompaniesFragment.newInstance(owners);
        }
        return CompaniesFragment.newInstance(assets);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}