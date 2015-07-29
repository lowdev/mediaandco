package eu.entropy.mediapedia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.utils.SigmaFragment;

public class CompanyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Owners", "Assets", "Sigma graph" };
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
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return CompaniesFragment.newInstance(owners);
        }

        if (1 == position) {
            return CompaniesFragment.newInstance(assets);
        }

        return SigmaFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}